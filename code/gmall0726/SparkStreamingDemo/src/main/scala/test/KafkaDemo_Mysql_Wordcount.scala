package test

import java.sql.{Connection, PreparedStatement, ResultSet}
import com.atguigu.sparkstreaming.utils.JDBCUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

import scala.collection.mutable


object KafkaDemo_Mysql_Wordcount {

  val groupId = "KafkaDemo_Mysql_Wordcount"
  val topicName = "topicA"

  /*
  1.读取myswql，查询上次这个组消费某个主题的偏移量信息
  入参：groupId，topicName
  返回值：Map[TopicPartition,jl.Long]
   */
  def selectOffsetFromMysql(groupId:String,topicName:String):Map[TopicPartition,Long]={

  val offset = new mutable.HashMap[TopicPartition,Long]

  val sql=
    """
      |
      |select
      | partitionId,offset
      |from offsets
      |where
      |groupId = ? and topic =?
      |
      |
      |""".stripMargin
  var connection: Connection = null
  var ps: PreparedStatement = null


    try {
      connection = JDBCUtil.getConnection()
      ps = connection.prepareStatement(sql)
      ps.setString(1,groupId)
      ps.setString(2,topicName)
      val resultSet: ResultSet = ps.executeQuery()
      while(resultSet.next()){
      val topicpartition: TopicPartition = new TopicPartition(topicName,resultSet.getInt("partitionId"))
     offset.put(topicpartition,resultSet.getLong("offset"))

      }

    } catch {
      case e:Exception =>{
        e.printStackTrace()
        throw new RuntimeException("查询偏移量报错")
      }
    } finally {

      if(ps != null){
        ps.close()
      }
      if(connection != null){
        connection.close()
      }

    }
  //把可变集合转变成不可变集合
  offset.toMap
}

  /*
6.在driver端将计算的结果和当前批次的offset在一个事务中写入mysql
写数据
有状态计算：每个批次累加
        insert into wordcount(word,count)
        values(?,?)
        ON DUPLICATE KEY UPDATE word =VALUES(word),count = count + VALUES(count)
无状态计算：只计算当前批次
        insert into wordcount(word,count)
        values(?,?)
        ON DUPLICATE KEY UPDATE word =VALUES(word),count = VALUES(count)
 */
  def writeDateAndOffsetToMysql(result: Array[(String, Int)], range: Array[OffsetRange]): Unit = {

    val sql1 =
      """
        |insert into wordcount
        |values(?,?)
        |ON DUPLICATE KEY UPDATE word =VALUES(word),COUNT = COUNT+VALUES(COUNT)
        |""".stripMargin

    val sql2 =
      """
        |insert into offsets values(?,?,?,?)
        |ON DUPLICATE KEY UPDATE offset = VALUES(offset)
        |""".stripMargin

    var connection: Connection = null
    var ps1: PreparedStatement = null
    var ps2: PreparedStatement = null

    try {
      connection = JDBCUtil.getConnection()

      //取消事务自动提交改成手动提交
      connection.setAutoCommit(false)

      ps1 = connection.prepareStatement(sql1)
      ps2 = connection.prepareStatement(sql2)
      println("data开始foreach")

      result.foreach{
        case (word,count) =>{
          println("============="+result.length)
          println("data结束foreach"+"---"+word+"---"+count)
          ps1.setString(1,word)
          ps1.setLong(2,count)
          ps1.addBatch()

        }
      }
      println("offset开始foreach")
      range.foreach(offsetRange=>{
        ps2.setString(1,groupId)
        ps2.setString(2,offsetRange.topic)
        ps2.setInt(3,offsetRange.partition)
        ps2.setLong(4,offsetRange.untilOffset)
        ps2.addBatch()

      })
      val dataResult: Array[Int] = ps1.executeBatch()
      val offsetResult: Array[Int] = ps2.executeBatch()
      //提交事务
      connection.commit()

      println("写入成功数据"+dataResult.size)
      println("写入成功偏移量"+offsetResult.size)


    } catch {
      case e:Exception =>{
        e.printStackTrace()
      //有错误则回滚
        connection.rollback()
        throw new RuntimeException("插入数据报错")
      }
    } finally {

      if(ps1 != null){
        ps1.close()
      }
      if(ps2 != null){
        ps2.close()
      }
      if(connection != null){
        connection.close()
      }

    }

  }

  /*
  2.从上次消费的位置获取DStream
   */

  def main(args: Array[String]): Unit = {

  val streamingContext =new StreamingContext("local[*]","app1",Seconds(5));

  //定义kafka消费者参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> groupId,
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "false"
    )

    val topics = Array(topicName)
    //3.获取当前批次数据的偏移量信息
    val offset: Map[TopicPartition, Long] = selectOffsetFromMysql(groupId:String,topicName:String)

    //创建DS
    val ds: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams,offset)
    )
    //计算
    ds.foreachRDD(rdd=>{
    //获取当前偏移量
      val range = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      //4.对DStream进行wordcount运算
      val rdd1: RDD[(String, Int)] =
        rdd.flatMap(_.value()
          .split(" "))
          .map((_,1))
          .reduceByKey(_+_)
      //5.将结果拉取到driver端
      val result: Array[(String, Int)] = rdd1.collect()

      println("-------------------result长度"+result.size)
      result.foreach(println(_))
      //6.在driver端将计算的结果和当前批次的offset在一个事务中写入mysql
      writeDateAndOffsetToMysql(result,range)

    })



    streamingContext.start()
    streamingContext.awaitTermination()
    
  }
}
