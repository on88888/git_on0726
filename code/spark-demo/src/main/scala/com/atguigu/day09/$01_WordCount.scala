package com.atguigu.day09

object $01_WordCount {

  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")

    //从数据源读取数据
    val ds = ssc.socketTextStream("hadoop102",9999)
    //处理数据
/*    val ds2 = ds.flatMap(x=>{
      Thread.sleep(10000)
      x.split(" ")
    })
      .map((_,1))
      .reduceByKey(_+_)*/
    val ds2 = ds.transform(rdd=>{
      rdd.flatMap(_.split(" "))
        .map((_,1))
        .reduceByKey(_+_)
    })
    //展示
    ds2.print()
    //启动程序
    ssc.start()
    //阻塞
    ssc.awaitTermination()
  }
}
