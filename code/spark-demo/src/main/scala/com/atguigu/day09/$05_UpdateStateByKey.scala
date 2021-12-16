package com.atguigu.day09

object $05_UpdateStateByKey {

  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")
    ssc.checkpoint("checkpoint")

    val ds = ssc.socketTextStream("hadoop102",9999)

    val ds2 = ds.flatMap(_.split(" "))

    val ds3 = ds2.map((_,1))

    val func  = (currentBatchValues: Seq[Int], state:Option[Int]) =>{
      println(s"当前批次所有value值:${currentBatchValues}  状态:${state}")
      val totalCount = currentBatchValues.sum + state.getOrElse(0)

      Some(totalCount)
    }
    //updateStateByKey(func: (Seq[value值类型],Option[最终的结果的value值类型])=>Option[最终的结果的value值类型]): 分組聚合统计每个key的全局结果
    //  updateStateByKey函数的第一个参数是表示key在当前批次中所有value值的集合
    //  updateStateByKey函数的第二个参数是表示key在之前批次的聚合结果[因为当前批次的key在之前批次中可能没有出现过,所以是Option表示]
    val ds4 = ds3.updateStateByKey(func)

    ds4.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
