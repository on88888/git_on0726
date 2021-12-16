package com.atguigu.day09

import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

object $04_KafkaSource {

  def main(args: Array[String]): Unit = {


    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")

    //设置消费的topic信息
    val topics = Array("spark_kafka")
    //配置消费者参数
    val params = Map[String,Object](
      //设置key的反序列化器
      "key.deserializer"->"org.apache.kafka.common.serialization.StringDeserializer",
      //设置value值的反序列化器
      "value.deserializer"->"org.apache.kafka.common.serialization.StringDeserializer",
      //设置集群地址
      "bootstrap.servers"->"hadoop102:9092,hadoop103:9092,hadoop104:9092",
      //设置消费者组的id
      "group.id"->"spark_g1",
      //设置消费者组第一次消费topic数据的时候从哪个位置开始消费
      "auto.offset.reset"->"earliest",
      //是否自动提交offset
      "enable.auto.commit"->"true"

    )
    val ds = KafkaUtils.createDirectStream[String,String](ssc,LocationStrategies.PreferConsistent,ConsumerStrategies.Subscribe[String,String](topics,params))
    //sparkstreaming消费kafka数据的时候,rdd的分区数根据topic的分区数动态调整, rdd的分区数 = topic分区数, rdd一个分区就是消费者组中的一个消费者
    ds.foreachRDD( rdd=> {
      println(rdd.getNumPartitions)
      //读取kafka数据之后处理
      val rdd2 = rdd.flatMap(x=> x.value().split(" "))
        .map((_,1))
        .reduceByKey(_+_)
      //保存数据
      rdd2.collect().foreach(println)
    } )

    ssc.start()
    ssc.awaitTermination()
  }
}
