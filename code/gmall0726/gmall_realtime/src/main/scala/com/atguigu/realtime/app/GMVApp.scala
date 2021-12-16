package com.atguigu.realtime.app

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.alibaba.fastjson.JSON
import com.atguigu.gmall.constants.TopicName
import com.atguigu.realtime.app.DAUApp.{appName, batchDuration}
import com.atguigu.realtime.bean.OrderInfo
import com.atguigu.realtime.utils.{JDBCUtil, MyKafkaUtil}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * Created by Smexy on 2021/12/3
 *
 *    GMV:  at least once + 将结果和偏移量在一个事务中存储到mysql中实现精确一次
 */
object GMVApp extends BaseApp {
  override val appName: String = "GMVApp"
  override val batchDuration: Int = 10

  val groupId="gmall0726"

  def main(args: Array[String]): Unit = {

    context=new StreamingContext("local[*]",appName,Seconds(batchDuration))

    runApp{

      val offsetsMap: Map[TopicPartition, Long] = selectOffsetsFromMysql(groupId, TopicName.GMALL_ORDER_INFO)

      // 从上次消费的位置获取DS
      val ds: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(Array(TopicName.GMALL_ORDER_INFO), context, groupId, true, offsetsMap)

      ds.foreachRDD(rdd => {

        if (!rdd.isEmpty()){

          // 获取偏移量
          val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

          // value： {order_info}  将ConsumerRecord的value部分，解析为样例类
          val rdd1: RDD[OrderInfo] = rdd.map(record => {

            val formatter1: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("HH")
            val formatter3: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

            val orderInfo: OrderInfo = JSON.parseObject(record.value(), classOf[OrderInfo])

            // 根据 create_time  解析得到  create_date 和 create_hour
            // "2021-12-01 20:10:42"   不知道这玩意是什么格式
            val dateTime: LocalDateTime = LocalDateTime.parse(orderInfo.create_time,formatter3)
            orderInfo.create_date = dateTime.format(formatter1)
            orderInfo.create_hour = dateTime.format(formatter2)

            orderInfo

          })

          // 计算每一天每个小时，订单成交的总额
          val rdd2: RDD[((String, String), Double)] = rdd1.map(orderInfo => ((orderInfo.create_date, orderInfo.create_hour), orderInfo.total_amount))
            .reduceByKey(_ + _)

          // 将结果和偏移量在一个事务中写出
          val result: Array[((String, String), Double)] = rdd2.collect()

          writeDataAndOffsetsToMysql(result,ranges)

        }


      })



    }

  }

  def writeDataAndOffsetsToMysql(result: Array[((String, String), Double)], ranges: Array[OffsetRange]): Unit = {


    /*
        写数据

         有状态的计算，同一天同一个小时的GMV需要累加
     */
    val sql1=
      """
        |INSERT INTO gmvstats VALUES(?,?,?)
        |ON DUPLICATE KEY UPDATE   gmv=gmv + VALUES(gmv)
        |
        |
        |""".stripMargin

    val sql2=
      """
        |
        |INSERT INTO offsets values(?,?,?,?)
        |ON DUPLICATE KEY UPDATE offset = values(offset)
        |
        |
        |""".stripMargin

    var connection: Connection = null
    var ps1: PreparedStatement = null
    var ps2: PreparedStatement = null

    try {

      connection = JDBCUtil.getConnection()

      // begin  取消事务的自动提交，改为手动提交
      connection.setAutoCommit(false)

      ps1 = connection.prepareStatement(sql1)
      ps2 = connection.prepareStatement(sql2)

      result.foreach{
        case ((date, hour), gmv) =>{

          //写入一行
          ps1.setString(1,date)
          ps1.setString(2,hour)
          ps1.setDouble(3,gmv)

          //攒起来
          ps1.addBatch()

        }
      }

      ranges.foreach(offsetRange => {

        // 一行
        ps2.setString(1,groupId)
        ps2.setString(2,offsetRange.topic)
        ps2.setInt(3,offsetRange.partition)
        ps2.setLong(4,offsetRange.untilOffset)

        ps2.addBatch()

      })

      //写入
      val dataResult: Array[Int] = ps1.executeBatch()
      val offsetResult: Array[Int] = ps2.executeBatch()

      //提交事务
      connection.commit()

      println("写入成功数据:"+dataResult.size)
      println("写入成功offset:"+offsetResult.size)


    } catch {
      case e: Exception => {

        e.printStackTrace()

        connection.rollback()

        throw new RuntimeException("写入报错!")

      }
    } finally {

      if (ps1 != null) {

        ps1.close()
      }

      if (ps2 != null) {

        ps2.close()
      }

      if (connection != null) {

        connection.close()

      }

    }
  }


  def selectOffsetsFromMysql(groupId :String, topicName: String) : Map[TopicPartition,Long]={

    // TopicPartition: 分区和主题
    val offsets = new mutable.HashMap[TopicPartition, Long]()

    val sql=
      """
        |
        |select
        |    partitionId,offset
        |from offsets
        |where groupId = ? and topic=?
        |
        |
        |
        |""".stripMargin

    var connection: Connection = null
    var ps: PreparedStatement = null

    try {

      connection = JDBCUtil.getConnection()
      ps  = connection.prepareStatement(sql)

      ps.setString(1,groupId)
      ps.setString(2,topicName)

      val resultSet: ResultSet = ps.executeQuery()

      while(resultSet.next()){
        //一行代理一个组消费一个主题的一个分区的信息
        val topicPartition = new TopicPartition(topicName, resultSet.getInt("partitionId"))

        offsets.put(topicPartition, resultSet.getLong("offset"))

      }


    } catch {
      case e:Exception =>{

        e.printStackTrace()

        throw new RuntimeException("查询偏移量报错!")

      }
    } finally {

      if (ps != null){

        ps.close()
      }

      if (connection != null){

        connection.close()

      }

    }


    //可变map转不可变map
    offsets.toMap

  }
}
