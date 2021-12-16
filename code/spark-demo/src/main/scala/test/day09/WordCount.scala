package test.day09

/**
 * @Author 0726
 * @ClassName WordCount
 * @createTime 2021年11月07日 11:08
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    //1.创建SparkConf
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建StreamingContext                  //批次时间5秒
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error")  //日志级别：debug  info  warn  error (默认info)

    //从数据源读取数据
    val ds = ssc.socketTextStream("hadoop102",9999)
    //处理数据
    val ds2 = ds.flatMap(x=>{
      Thread.sleep(10000)
      x.split(" ")
    })
      .map((_,1))
      .reduceByKey(_+_)
    //展示
    ds2.print()
    //启动程序
    ssc.start()
    //阻塞
    ssc.awaitTermination()
  }
}
