package com.atguigu.realtime.app

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import java.util

import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.gmall.constants.TopicName
import com.atguigu.realtime.app.AlertApp.{appName, batchDuration, context}
import com.atguigu.realtime.bean.{OrderDetail, OrderInfo, SaleDetail, UserInfo}
import com.atguigu.realtime.utils.{MyKafkaUtil, RedisUtil}
import com.google.gson.Gson
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis

import scala.collection.mutable.ListBuffer
import org.elasticsearch.spark._

/**
 * Created by Smexy on 2021/12/6
 *
 *    精确一次： at least once + 幂等输出
 *
 *
 *    双流Join的注意事项：
 *        ①两个DS必须从同一个StreamingContext获取
 *        ②只有 DS[K,V]才能Join
 *            K: 关联的字段  orderId
 *            order_info  join  order_detail on order_info.id = order_detail.order_id
 */
object SaleDetailApp extends BaseApp {
  override val appName: String = "SaleDetailApp"
  override val batchDuration: Int = 10

  val groupId="0726gmall"

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName(appName)

    //设置和es相关的参数   在写入数据时，如果要写入的index不存在，是否允许自动创建
    sparkConf.set("es.index.auto.create", "true")
    //集群地址
    sparkConf.set("es.nodes","hadoop102")
    sparkConf.set("es.port ","9200")

    context=new StreamingContext(sparkConf,Seconds(batchDuration))

    runApp{

      val ds1: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(Array(TopicName.GMALL_ORDER_INFO), context, groupId)
      val ds2: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(Array(TopicName.GMALL_ORDER_DETAIL), context, groupId)

      var orderInfoOffsetRanges: Array[OffsetRange] =null
      var orderDetailOffsetRanges: Array[OffsetRange] =null

      // 将数据封装为bean，转换为 DS[K,V]
      val ds3: DStream[(String, OrderInfo)] = ds1.transform(rdd => {

        orderInfoOffsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

        rdd.map(record => {

          val orderInfo: OrderInfo = JSON.parseObject(record.value(), classOf[OrderInfo])

          val formatter1: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
          val formatter3: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

          val dateTime: LocalDateTime = LocalDateTime.parse(orderInfo.create_time,formatter3)
          orderInfo.create_date = dateTime.format(formatter1)

          (orderInfo.id, orderInfo)

        })

      })

      val ds4: DStream[(String, OrderDetail)] = ds2.transform(rdd => {

        orderDetailOffsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

        rdd.map(record => {

          val orderDetail: OrderDetail = JSON.parseObject(record.value(), classOf[OrderDetail])
          (orderDetail.order_id, orderDetail)

        })

      })

      /*
          对于Join后的order_info:
              ①和当前批次的order_detail关联
              ②可能晚到，需要到缓存中找早到的order_detail关联
                          set
              ③可能早到，需要将自己写入缓存
                          string


          对于Order_detail:
                ①和当前批次的order_info关联
                ②如果①关联不上，说明当前的order_detail早到或晚到
                    晚到：  到缓存，找是否有早到的order_info，有就关联
                    早到：  在缓存中，找不到对应的order_info，说明当前的order_detail早到，将order_detail写入缓存


       */
      // 两个流Join后的数据
      val ds5: DStream[(String, (Option[OrderInfo], Option[OrderDetail]))] = ds3.fullOuterJoin(ds4)


      val ds6: DStream[SaleDetail] = ds5.mapPartitions(partition => {
        val jedis: Jedis = RedisUtil.getJedisClient()

        //准备集合存在关联成功的SaleDetail
        val saleDetails = new ListBuffer[SaleDetail]

        partition.foreach {
          case (orderId, (orderInfoOption, orderDetailOption)) => {

            val gson = new Gson()

            if (orderInfoOption != None) {

              val oi: OrderInfo = orderInfoOption.get

              //  ①和当前批次的order_detail关联
              if (orderDetailOption != None) {

                val od: OrderDetail = orderDetailOption.get

                val saleDetail = new SaleDetail(oi, od)

                saleDetails.append(saleDetail)

              }

              //②可能晚到，需要到缓存中找早到的order_detail关联
              val ods: util.Set[String] = jedis.smembers("orderDetail:" + orderId)

              ods.forEach(odStr => {

                val orderDetail: OrderDetail = JSON.parseObject(odStr, classOf[OrderDetail])

                val saleDetail = new SaleDetail(oi, orderDetail)

                saleDetails.append(saleDetail)

              })

              //    ③可能早到，需要将自己写入缓存   假设压测后最大延迟是5min
              jedis.setex("orderInfo:" + orderId, 5 * 60 * 3, gson.toJson(oi))
            } else {

              val od: OrderDetail = orderDetailOption.get
              //  ①和当前批次的order_info关联

              //②如果①关联不上，说明当前的order_detail早到或晚到
              //  晚到：  到缓存，找是否有早到的order_info，有就关联
              val str: String = jedis.get("orderInfo:" + od.order_id)

              if (str != null) {

                val oi: OrderInfo = JSON.parseObject(str, classOf[OrderInfo])

                val saleDetail = new SaleDetail(oi, od)

                saleDetails.append(saleDetail)

              } else {

                //早到：  在缓存中，找不到对应的order_info，说明当前的order_detail早到，将order_detail写入缓存
                jedis.sadd("orderDetail:" + orderId, gson.toJson(od))

                jedis.expire("orderDetail:" + orderId, 5 * 60 * 3)

              }


            }


          }
        }

        jedis.close()

        saleDetails.toIterator
      })

      // ds6是已经将orderInfo 和 orderDetail关联后的DS
      val ds7: DStream[SaleDetail] = ds6.mapPartitions(partition => {

        val jedis: Jedis = RedisUtil.getJedisClient()

        val iterator: Iterator[SaleDetail] = partition.map(saleDetail => {

          val str: String = jedis.get("userinfo:" +saleDetail.user_id)

          if (str != null) {

            val userInfo: UserInfo = JSON.parseObject(str, classOf[UserInfo])

            saleDetail.mergeUserInfo(userInfo)

          }

          saleDetail

        })

        jedis.close()

        iterator

      })

      //写入ES
      ds7.foreachRDD(rdd => {


        rdd.cache()

        println("写入ES:"+rdd.count())

        rdd.saveToEs("gmall2021_sale_detail"+LocalDate.now()+"/_doc",Map("es.mapping.id" -> "order_detail_id"))

        ds1.asInstanceOf[CanCommitOffsets].commitAsync(orderInfoOffsetRanges)
        ds2.asInstanceOf[CanCommitOffsets].commitAsync(orderDetailOffsetRanges)

      })

    }


  }
}
