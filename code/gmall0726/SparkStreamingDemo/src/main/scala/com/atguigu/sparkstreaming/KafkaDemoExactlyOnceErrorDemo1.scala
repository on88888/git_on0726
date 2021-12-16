package com.atguigu.sparkstreaming

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 *
 * Exception in thread "main" java.lang.ClassCastException:
 *
 *      org.apache.spark.rdd.MapPartitionsRDD
 *        cannot be cast to
 *     org.apache.spark.streaming.kafka010.HasOffsetRanges
 *
 *
 *     结论：   如果想调用
 *        ds1.foreachRDD(rdd => {
 *      val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
 *      })获取偏移量，那么 ds1必须是初始DS(原汁原味)的 DS！
 *
 *
 */
object KafkaDemoExactlyOnceErrorDemo1 {

  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext("local[*]", "app1", Seconds(5))

    // 定义kafka消费者参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "0726test1",
      "auto.offset.reset" -> "latest",
      //取消自动提交
      "enable.auto.commit" -> "false"
    )

    val topics = Array("topicA")

    //创建DS
    val ds: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

     //  MappedDStream 里面封装的RDD都是   MapPartitionsRDD
    val ds1: DStream[String] = ds.map(record => record.value())

    ds1.foreachRDD(rdd => {

      /*
            报错的地方  rdd是MapPartitionsRDD，无法转换为HasOffsetRanges

                A.asInstanceOf[B]: 判断A是不是B的子类，如果是，就转换为B类型！

                仅有 rdd是 KafkaRDD类型时，才能通过！
       */
      val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges


      val rdd1: RDD[(String, Int)] = rdd.flatMap(line => {
        line.split(" ")
      }).map(word => (word, 1))
        .reduceByKey(_ + _)

      rdd1.foreach(println(_))


    })

    streamingContext.start()

    streamingContext.awaitTermination()

  }

}
