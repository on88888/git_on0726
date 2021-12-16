package com.atguigu.sparkstreaming

import java.sql.{Connection, PreparedStatement, ResultSet}

import com.atguigu.sparkstreaming.utils.JDBCUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * Created by Smexy on 2021/11/29
 *
 *
 *    wordcount 属于聚合类运算！
 *
 *        使用Mysql存储计算的结果和偏移量！
 *
 *        ①建表
 *            存储结果:
 *                    word   varchar(20)   主键
 *                    count  Bigint
 *
 *                  粒度： 一个单词是一行
 *            存储偏移量
 *                    groupId varchar(10)
 *                    topic varchar(10)
 *                    partitionId int
 *
 *                                      主键
 *                    offset  Bigint
 *
 *                  粒度： 一个groupId消费一个topic的一个partitionid是一行
 *
 *        ②加依赖
 *            mysql的依赖
 *            druid的依赖
 *
 *        ③流程
 *              a)  读取mysql，查询上次这个组消费某个主题的偏移量信息
 *                        offsets: ju.Map[TopicPartition, jl.Long]
 *              b)  从上次消费的位置，获取DStream
 *              c)   获取当前批次数据的便移量信息
 *              d)    对DStream进行 wordcount运算
 *              e)   将计算结果拉取到driver端
 *              f)  在driver端，将计算的结果和当前批次的offsets在一个事务中写入到mysql
 *
 *
 *

 */
object KafkaDemoExactlyOnceDemo4 {

  val groupId = "0726test1"
  val topicName ="topicA"


  /*
      读取mysql，查询上次这个组消费某个主题的偏移量信息
        入参：
              groupId，topicName

        返回值：ju.Map[TopicPartition, jl.Long]
   */
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

  def writeDataAndOffsetsToMysql(result: Array[(String, Int)], ranges: Array[OffsetRange]): Unit = {


    /*
        写数据

          有状态的计算：   每个批次累加
              INSERT INTO wordcount VALUES('a',4)
              ON DUPLICATE KEY UPDATE  word=VALUES(word), COUNT=COUNT + VALUES(COUNT)

          无状态的计算：  只计算当前批次
              INSERT INTO wordcount VALUES('a',4)
                  ON DUPLICATE KEY UPDATE  word=VALUES(word), COUNT=VALUES(COUNT)
     */
    val sql1=
      """
        |INSERT INTO wordcount VALUES(?,?)
        |ON DUPLICATE KEY UPDATE  word=VALUES(word), COUNT=COUNT + VALUES(COUNT)
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
        case (word, count) =>{
          println("word" + word + "---" + count)

          //写入一行
          ps1.setString(1,word)
          ps1.setLong(2,count)

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

  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext("local[*]", "app1", Seconds(5))

    // 定义kafka消费者参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> groupId,
      "auto.offset.reset" -> "latest",
//      "auto.offset.reset" -> "earliest",
      //取消自动提交
      "enable.auto.commit" -> "false"
    )

    val topics = Array(topicName)

    val offsets: Map[TopicPartition, Long] = selectOffsetsFromMysql(groupId, topicName)

    //创建DS
    val ds: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      //消费方式的指定
      // 从上一次消费的位置获取DS
      Subscribe[String, String](topics, kafkaParams,offsets)
    )


     ds.foreachRDD(rdd => {

       //判断当前批次的RDD中是否消费到了数据
       if (!rdd.isEmpty()){

         // 获取当前批次偏移量
         val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

         // 单词统计
         val rdd1: RDD[(String, Int)] = rdd.flatMap(_.value().split(" ")).map((_, 1)).reduceByKey(_ + _)

         // 把计算的结果拉取到driver端
         val result: Array[(String, Int)] = rdd1.collect()

         //将偏移量和结果在一个事务中写到数据库
         writeDataAndOffsetsToMysql(result,ranges)
       }

    })


    streamingContext.start()

    streamingContext.awaitTermination()

  }

}
