package com.atguigu.day09

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

object $08_Close {
  /**
    * sparkstreaming程序提交到集群之后,如果想要升级代码,最好不要强行kill程序,因为可能会导致数据丢失。
    *   最好是等待sparkstreaming将拉取的批次数据全部处理完成之后停止。
    *
    */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")

    ssc.socketTextStream("hadoop102",9999)
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()
    //此时想要停止程序可以通过监控mysql表某个字段的值,如果字段的值发生改变则停止程序
    //此时想要停止程序可以通过监控HDFS某个目录是否删除,如果目录删除则停止程序
    val fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),new Configuration())
    while(fs.exists(new Path("hdfs://hadoop102:8020/input"))){
      Thread.sleep(5000)
    }
    ssc.stop(true,true)
    //stopGracefully: 是等到拉取的数据全部处理完成才会停止sparkstreaming程序
    ssc.awaitTermination()

    //spark-submit --class .. --master --..  xxx.jar true

  }
}
