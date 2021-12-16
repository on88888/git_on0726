package test
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
/**
 * @Author 0726
 * @ClassName KafkaTest1
 * @createTime 2021年11月29日 18:54
 */
object KafkaTest1 {
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext("local[*]", "app2", Seconds(5))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "0726test2",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "true"
    )

    val topics = Array("topicAA")


    val stream = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    //做计算
    val ds1 = stream.flatMap(record => {
      record.value().split(" ")
    }).map(word => (word, 1))
      .reduceByKey(_ + _)

    ds1.print(100)

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
