package test.day09

import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

/**
 * @Author 0726
 * @ClassName KafkaSource
 * @createTime 2021年11月07日 13:16
 */
object KafkaSource {
  def main(args: Array[String]): Unit = {

    import org.apache.spark.SparkConf
    import org.apache.spark.streaming.{Seconds, StreamingContext}
    //1.创建SparkConf
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建StreamingContext
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("error") //日志级别：debug  info  warn  error (默认info)

    val topics = Array("spark_kafka")

//    KafkaUtils.createDirectStream[String,String](ssc,LocationStrategies.PreferConsistent,ConsumerStrategies.Subscribe[String,String])
  }
}
