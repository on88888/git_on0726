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
 *     def foreachRDD(foreachFunc: RDD[T] => Unit): Unit
 *
 *    def transform[U: ClassTag](transformFunc: RDD[T] => RDD[U]): DStream[U]
 *
 *      异:  返回值。
 *              foreachRDD 没有返回值，是一个输出的算子，将数据写入数据库！
 *
 *           transform 的作用是对DS[T] 进行转换，转换为 DS[U]，
 *              转换函数，是对 DS[T] 中的 RDD[T] ,进行操作，得到 RDD[U]
 *
 *      同:  都是将对DS的操作，转换为对DS中所封装RDD的操作！
 *              调用这两个算子，最终在计算时，都是调用RDD的算子来计算！
 *
 *      ----------------------
 *        为什么要设计transform？
 *            DStream的算子是有限的，没有RDD的算子丰富！
 *              例如调用 sortByKey的算子！
 *
 *                val ds2: DStream[(String, Int)] = ds1.transform(rdd => rdd.sortByKey())
 *
 *              将DStream的转换，转化为RDD的转换
 *
 *              DStream.transform( rdd => rdd.sortByKey())
 */
object KafkaDemoExactlyOnceDemo2 {

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

    ds.foreachRDD(rdd => {

      val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges


      val rdd1: RDD[(String, Int)] = rdd.flatMap(record => {
        record.value().split(" ")
      }).map(word => (word, 1))
        .reduceByKey(_ + _)

      rdd1.foreach(println(_))
      //幂等输出

      ds.asInstanceOf[CanCommitOffsets].commitAsync(ranges)


    })

    streamingContext.start()

    streamingContext.awaitTermination()

  }

}
