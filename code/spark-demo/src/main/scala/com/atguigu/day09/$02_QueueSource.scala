package com.atguigu.day09

import org.apache.spark.rdd.RDD

import scala.collection.mutable

object $02_QueueSource {

  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")
    //从队列中读取数据
    val queue = mutable.Queue[RDD[String]]()
    //oneAtATime: 是否每个批次只从队列中取出一个rdd处理[默认true,代表一次支取一个rdd处理, false代表取出批次时间内所有的rdd处理]
    val ds = ssc.queueStream(queue,false)

    ds.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()

    for(i<- 1 to 100){
      val rdd = ssc.sparkContext.parallelize(List("hello spark hello hadoop","hadoop flume flume spark"))
      queue.enqueue(rdd)
      Thread.sleep(2000)
    }
    ssc.awaitTermination()
  }
}
