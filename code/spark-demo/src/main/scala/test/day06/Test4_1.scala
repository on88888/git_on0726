package test.day06

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName Test4_1
 * @createTime 2021年11月03日 20:18
 */
object Test4_1 {

  /**
   * 广播变量：broadcast
   * @param args
   */
  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)
    val rdd1: RDD[String] = sc.parallelize(List("jd","pdd","tm","atguigu"))
    val map = Map[String,String]("jd"->"www.jd.com","pdd"->"www.pdd.com","tm"->"www.tm.com","atguigu"->"www.atguigu.com")

    val bc: Broadcast[Map[String, String]] = sc.broadcast(map)

    val rdd2 = rdd1.map(x=>{
      val value: Map[String, String] = bc.value
      //      println(s"${Thread.currentThread().getName} -- ${x}  --${value}")
      value.getOrElse(x,"")
    })

    println(rdd2.collect().toList)

    Thread.sleep(1000000)
  }
}
