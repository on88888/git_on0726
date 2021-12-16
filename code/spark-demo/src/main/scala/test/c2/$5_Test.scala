package test.c2

import org.apache.spark.rdd.RDD
import org.junit.Test

/**
 * @Author 0726
 * @ClassName $5_Test
 * @createTime 2021年11月02日 18:09
 */
class $5_Test {
  import org.apache.spark.{SparkConf, SparkContext}
  //1.创建SparkConf并设置App名称
  val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
  //2.创建SparkContext，该对象是提交Spark App的入口
  val sc: SparkContext = new SparkContext(conf)

  @Test
  def reduce(): Unit ={
    val rdd1: RDD[Int] = sc.parallelize(List(1,3,5,6,7,8,9),2)
    val rdd2: Int = rdd1.reduce( (agg,curr) => {
      println(s"${Thread.currentThread().getName} --agg = ${agg}  curr = ${curr}")
      agg+curr
    } )
    println(rdd2)

    println(rdd1.count())
  }

  @Test
  def countByKey(): Unit ={

    val rdd1: RDD[String] = sc.textFile("datas/wc.txt")
    val add2: RDD[(String, Int)] = rdd1.flatMap(_.split(" ")).map(x=>(x,1))
    val add3: RDD[Array[String]] = rdd1.map(_.split(" "))
//    add2.collect().toList.foreach(println)
    val map = add2.countByKey()
    println(map)
    map.foreach(println)


  }



}
