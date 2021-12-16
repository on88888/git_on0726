package com.atguigu.realtime.app

import java.time.{Instant, LocalDate, LocalDateTime, ZoneId}
import java.time.format.DateTimeFormatter
import java.util

import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.gmall.constants.TopicName
import com.atguigu.realtime.bean.{Action, ActionsLog, CommonInfo, CouponAlertInfo}
import com.atguigu.realtime.utils.MyKafkaUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

// 导入了常用的java集合和scala集合转换的方法   java集合转scala集合 ：  java集合.asScala
//  scala集合转 java集合  scala集合.asJava
import collection.JavaConverters._


//导入es提供的和spark整合的对象的静态方法
import org.elasticsearch.spark._

/**
 * Created by Smexy on 2021/12/4
 *
 *    三个时间：
 *            每批次数据采集的时间间隔:  BatchDuration   10s
 *            要计算的数据的时间范围：  window    5min
 *            计算的Job每间隔多久提交一次:  slide   默认和BatchDuration相等
 *
 *            slide和window必须是 BatchDuration的整倍数!
 *
 *
 *    预警日志要求写入ES
 *
 *        精确一次：  at least once + 输出幂等
 *
 *        PUT  /index1/type1/1 {a}
 *         PUT  /index1/type1/1 {a}
 *          PUT  /index1/type1/1 {a}
 *           PUT  /index1/type1/1 {a}
 *
 */
object AlertApp extends  BaseApp {
  override val appName: String = "AlertApp"
  override val batchDuration: Int = 10

  val groupId="gmall0726"

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName(appName)

    //设置和es相关的参数   在写入数据时，如果要写入的index不存在，是否允许自动创建
    sparkConf.set("es.index.auto.create", "true")
    //集群地址
    sparkConf.set("es.nodes","hadoop102")
    sparkConf.set("es.port ","9200")

    context=new StreamingContext(sparkConf,Seconds(batchDuration))

    runApp{

      // ①消费数据获取DS
      val ds: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(Array(TopicName.ACTIONS_LOG), context, groupId)

      var ranges: Array[OffsetRange] = null

      // ②转换为样例类
      val ds1: DStream[ActionsLog] = ds.transform(rdd => {

        //获取当前批次的偏移量
        ranges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

        rdd.map(record => {

          val jSONObject: JSONObject = JSON.parseObject(record.value())

          val commonInfo: CommonInfo = JSON.parseObject(jSONObject.getString("common"), classOf[CommonInfo])


          // 有 util.List[Action]  --->  scala.collection.immutable.List[A]
          val actions: List[Action] = JSON.parseArray(jSONObject.getString("actions"), classOf[Action]).asScala.toList

          ActionsLog(actions, jSONObject.getLong("ts"), commonInfo)

        })

      })

      // ③ 采集过去5分钟的数据，按照 (设备,用户) 分组
      val ds2: DStream[((String, Long), Iterable[ActionsLog])] = ds1.window(Minutes(5),Seconds(30))
        .map(actionsLog => ((actionsLog.common.mid, actionsLog.common.uid), actionsLog))
        .groupByKey()


      // ④ ((String, Long), Iterable[ActionsLog])： 代表一个用户在过去5分钟，在一个设备上产生的所有行为记录
      //  判断这个用户的行为记录中是否包含了  trade_add_address ，只留下包含了trade_add_address行为的用户和其行为记录
      val ds3: DStream[((String, Long), Iterable[ActionsLog])] = ds2.filter {

        //  { actionsLog [action,action],   actionsLog..    }
        case ((mid, uid), actionsLogs) => {

          var ifNeedAlert = false

          Breaks.breakable {

            actionsLogs.foreach(actionsLog => {

              actionsLog.actions.foreach(action => {

                if ("trade_add_address".equals(action.action_id)) {

                  ifNeedAlert = true

                  // 一旦产生了trade_add_address行为，后续无需判断
                  Breaks.break
                }

              })

            })

          }

          ifNeedAlert

        }
      }

      // ⑤将上述过滤得到的数据，继续按照设备同组，统计每个设置中每组的元素数(用户数)，判断是否>=2
      val ds4: DStream[(String, Iterable[Iterable[ActionsLog]])] = ds3.map {
        case ((mid, uid), actionsLogs) => (mid, actionsLogs)
      }.groupByKey()


      // (String, Iterable[Iterable[ActionsLog]])
      //  Iterable[Iterable[ActionsLog]]  {  {u1,5分钟的ActionsLog  },{u2,5分钟的ActionsLog}    }
      //   { ActionsLog,.... ActionsLog }
      val ds5: DStream[(String, Iterable[ActionsLog])] = ds4.filter(_._2.size >= 2)
        .mapValues(_.flatten)

      // ⑥生成预警日志
      val ds6: DStream[CouponAlertInfo] = ds5.map {
        case (mid, actionsLogs) => {

          var uids: mutable.Set[String] = new mutable.HashSet[String]()
          var itemIds: mutable.Set[String] = new mutable.HashSet[String]()
          var events: ListBuffer[String] = new ListBuffer[String]

          actionsLogs.foreach(actionsLog => {

            uids.add(actionsLog.common.uid.toString)

            actionsLog.actions.foreach(action => {

              events.append(action.action_id)

              if ("favor_add".equals(action.action_id)) {

                itemIds.add(action.item)

              }

            })

          })

          /*
              id:  mid

                mid1_2021-12-04 15:05:10  , 预警日志1
                mid1_2021-12-04 15:05:11  , 预警日志2

                一分钟只能留1条

                id:  mid1_2021-12-04 15:05

                PUT  /index/type/id

           */
          val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

          val ts: Long = System.currentTimeMillis()

          val time: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.of("Asia/Shanghai"))
          val minute: String = time.format(formatter)

          CouponAlertInfo(mid + "_" + minute, uids, itemIds, events, ts)
        }
      }


      // ⑦写入ES
      ds6.foreachRDD(rdd => {

        rdd.cache()

        println("即将写入:"+rdd.count())
        // EsSpark.saveToEs(rdd, "spark/docs", Map("es.mapping.id" -> "id"))
        // index名称，index的一些设置
        // rdd[T]: T类型的哪个属性，是作为写入指定index的id属性
        rdd.saveToEs("gmall_coupon_alert"+ LocalDate.now + "/_doc" ,Map("es.mapping.id" -> "id") )

        //提交偏移量
        ds.asInstanceOf[CanCommitOffsets].commitAsync(ranges)

      })


    }

  }
}
