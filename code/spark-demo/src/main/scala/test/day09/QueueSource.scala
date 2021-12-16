package test.day09

import org.apache.spark.rdd.RDD

import scala.collection.mutable

/**
 * @Author 0726
 * @ClassName QueueSource
 * @createTime 2021年11月07日 11:28
 */
object QueueSource {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    //1.创建SparkConf
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建StreamingContext
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error") //日志级别：debug  info  warn  error (默认info)

    //从队列中读取数据
    val queue = mutable.Queue[RDD[String]]()
    val ds = ssc.queueStream(queue,false)

    ds.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()

    for (i<-1 to 100){
      val rdd = ssc.sparkContext.parallelize(List("hello spark hello hadoop","hadoop flume spark flume"))
      queue.enqueue(rdd)
      Thread.sleep(2000)
    }
    ssc.awaitTermination()
  }
}
