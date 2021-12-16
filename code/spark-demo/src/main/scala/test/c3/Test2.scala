package test.c3

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator

/**
 * @Author 0726
 * @ClassName Test2
 * @createTime 2021年11月03日 10:17
 */
object Test2 {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val sum: LongAccumulator = sc.longAccumulator("sum")

    val rdd1: RDD[String] = sc.textFile("datas/wc.txt")

    val rdd2: RDD[(String, Int)] = rdd1.flatMap(_.split(" ")).map((_,1))

    var a = 0
    val r = rdd1.reduce((agg,curr)=>agg+curr)
//    rdd1.foreach(x=>sum.add(x))

    println(sum.value)
    println(r)

  }
}
