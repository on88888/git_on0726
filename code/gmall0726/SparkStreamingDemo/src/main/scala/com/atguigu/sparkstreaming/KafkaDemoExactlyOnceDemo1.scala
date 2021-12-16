package com.atguigu.sparkstreaming

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
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
 *            DStream的算子是<有限的>，没有RDD的算子<丰富>！
 *              例如调用 sortByKey的算子！
 *
 *                val ds2: DStream[(String, Int)] = ds1.transform(rdd => rdd.sortByKey())
 *
 *              将DStream的转换，转化为RDD的转换
 *
 *              DStream.transform( rdd => rdd.sortByKey())
 *
 *        <DStream是rdd的集合>
 *     ---------------------------
 * Exception in thread "main" java.lang.ClassCastException:
 *        org.apache.spark.streaming.dstream.ShuffledDStream
 *            cannot be cast to
 *            org.apache.spark.streaming.kafka010.CanCommitOffsets
 *
 *            结论： 在运行 ds2.asInstanceOf[CanCommitOffsets].commitAsync(ranges) 提交便宜量时，
 *                    ds2必须是初始DS(原汁原味的 DS)
 *
 *     ----------------------
 *        偏移量是提交到了kafka的 __consumer_offsets 主题中！
 *
 *
 */
object KafkaDemoExactlyOnceDemo1 {

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

    //创建DS   DS是DirectKafkaInputDStream
    val ds: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    var ranges: Array[OffsetRange] = null

    /*
     OffsetRange : 代表所消费的一个主题的一个分区的偏移量信息

       final class OffsetRange private(
 val topic: String      主题名,
 val partition: Int,    分区id
 val fromOffset: Long,   起始偏移量
 val untilOffset: Long   终止偏移量

    */
    val ds1: DStream[ConsumerRecord[String, String]] = ds.transform(rdd => {

      ranges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

      rdd

    })


   val ds2: DStream[(String, Int)] = ds1.flatMap(record => {
      record.value().split(" ")
    }).map(word => (word, 1))
      .reduceByKey(_ + _)

    ds2.foreachRDD(rdd => {

      //输出
      rdd.foreach(println(_))
      // 幂等输出

      //Executor 去 提交offsets
      // 只有  ds2 是  DirectKafkaInputDStream类型时，才能转换为 CanCommitOffsets
//      ds2.asInstanceOf[CanCommitOffsets].commitAsync(ranges)
      ds.asInstanceOf[CanCommitOffsets].commitAsync(ranges)

    })

   // ds2.print(100)

    //错误的写法，在Driver端
    //ds.asInstanceOf[CanCommitOffsets].commitAsync(ranges)

    streamingContext.start()

    streamingContext.awaitTermination()

  }

}
