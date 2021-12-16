package test.day09

/**
 * @Author 0726
 * @ClassName UserDefineSource
 * @createTime 2021年11月07日 11:39
 */
object UserDefineSource {
  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    //1.创建SparkConf
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建StreamingContext
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error") //日志级别：debug  info  warn  error (默认info)

    val ds = ssc.receiverStream(new MyReceiver("hadoop102",9999))

    ds.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()
    ssc.awaitTermination()

  }
}
