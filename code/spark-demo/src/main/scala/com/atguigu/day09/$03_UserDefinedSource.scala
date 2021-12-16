package com.atguigu.day09

object $03_UserDefinedSource {

  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")

    val ds = ssc.receiverStream(new MyReceiver("hadoop102",9999))

    ds.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()
    ssc.awaitTermination()
  }
}
