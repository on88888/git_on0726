package com.atguigu.sparkstreaming

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
 * Created by Smexy on 2021/11/29
 */
object KafkaDemo1 {

  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext("local[*]", "app1", Seconds(5))

    // 定义kafka消费者参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "0726test1",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "true"
    )

    val topics = Array("topicA")

    //创建DS
    val ds: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    /*
        计算
            line1： a b c a b c  ---> {a,b,c,a,b,c}
            line2:   a b c      -----> {a,b,c}

              { {a,b,c,a,b,c} , {a,b,c}  }  ----->扁平化 { a,b,c,a,b,c , a,b,c  }
     */
    val ds1: DStream[(String, Int)] = ds.flatMap(record => {
      record.value().split(" ")
    }).map(word => (word, 1))
      .reduceByKey(_ + _)


    ds1.print(100)

    streamingContext.start()

    streamingContext.awaitTermination()

  }

}
