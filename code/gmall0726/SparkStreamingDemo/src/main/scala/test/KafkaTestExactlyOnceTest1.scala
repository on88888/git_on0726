package test

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Author 0726
 * @ClassName KafkaTest1
 * @createTime 2021年11月29日 18:54
 */
object KafkaTestExactlyOnceTest1 {
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext("local[*]", "app2", Seconds(5))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "0726test2",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "false"
    )

    val topics = Array("topicAA")

    //创建DS
    val stream = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    var offsetRanges: Array[OffsetRange] = null

    stream.foreachRDD { rdd =>

      //获取当前批次偏移量
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

      offsetRanges.foreach(println(_))

      // some time later, after outputs have completed
//      stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    }


    //做计算
    val ds1 = stream.flatMap(record => {
      record.value().split(" ")
    }).map(word => (word, 1))
      .reduceByKey(_ + _)

    val ds2 = ds1.transform(rdd => rdd.sortByKey())

    ds1.print(100)

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
