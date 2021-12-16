package com.atguigu.day09

object $06_Window {

  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")

    ssc.checkpoint("checkpoint")
    val ds = ssc.socketTextStream("hadoop102",9999)

    val ds2 = ds.flatMap(_.split(" ")).map((_,1))
    //当前批次数据打印
    ds2.map(x=>(s"----->${x}",1)).print()
    //window(窗口长度,滑动长度): 窗口长度与滑动长度必须是批次时间的整数倍
    //val ds3 = ds2.window( Seconds(17),Seconds(5) )

    //val ds4 = ds3.reduceByKey(_+_)

    //val ds4 = ds2.reduceByKeyAndWindow(_+_, windowDuration=Seconds(15),Seconds(5))
    //针对场景: 窗口包含的批次特别多,滑动批次很少的时候,为了减少数据重复计算,此时可以采用此种方式提高计算性能[ 上一个窗口的结果-滑出批次结果+滑入批次的结果]
    val ds4 = ds2.reduceByKeyAndWindow((agg,curr)=>{
      println(s"滑入计算: agg=${agg}  curr=${curr}")
      agg+curr
    }, (agg,curr)=>{
      println(s"滑出计算: agg=${agg} curr=${curr}")
      agg-curr
    }, Seconds(25),Seconds(5))
    ds4.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
