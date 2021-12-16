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
 * Created by Smexy on 2021/11/29
 *
 *
 *    DS.foreachRDD | transform(rdd => {
 *
 *      在driver端运行
 *
 *      rdd.xxx(xxx => {
 *          在executor端运行
 *      })
 *
 *
 *
 *    })

 */
object KafkaDemoExactlyOnceDemo3 {

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

    /*
        转换算子: map,transform 都是转换算子
                    只有转换算子，会不会运行?

                    requirement failed: No output operations registered, so nothing to execute

        行动算子： print,foreachRDD
     */



     ds.foreachRDD(rdd => {

       // streaming-job-executor-0  driver端
       println(Thread.currentThread().getName)

       // 偏移量在Driver端存储
      val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

       ranges.foreach(range => println(Thread.currentThread().getName + ":"+range))

       val rdd1: RDD[String] = rdd.map(record => {
         // 在Executor端运行
         // :Executor task launch worker for task 2
         println("map:"+Thread.currentThread().getName)
         record.value()
       })

       rdd1.count()

    })



    streamingContext.start()

    streamingContext.awaitTermination()

  }

}
