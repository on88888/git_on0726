package test.day06

import org.apache.spark.rdd.RDD
import org.junit.Test

/**
 * @Author 0726
 * @ClassName Test2
 * @createTime 2021年11月03日 18:18
 */
class Test2 {
  import org.apache.spark.{SparkConf, SparkContext}
  //1.创建SparkConf并设置App名称
  val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
  //2.创建SparkContext，该对象是提交Spark App的入口
  val sc: SparkContext = new SparkContext(conf)

  /**
   * 文件读取
   * 需要填写对应的输出属性
   *    objectFile[String]
   *    sequenceFile[String, Int]
   */
  @Test
  def read(): Unit ={
    //读取文本
    println(sc.textFile("output/text").collect().toList)
    //读取对象文件
    println(sc.objectFile[String]("output/obj").collect().toList)
    //读取序列文件
    println(sc.sequenceFile[String, Int]("output/seq").collect().toList)
  }

  /**
   * 文件保存
   */
  @Test
  def write(): Unit ={
    val rdd1: RDD[String] = sc.textFile("datas/wc.txt")
    //将数据保存到文件文本中
    rdd1.saveAsTextFile("output/text")
    //保存到对象文件中
    rdd1.saveAsObjectFile("output/obj")
    //保存到序列文件中
    val rdd2: RDD[(String, Int)] = rdd1.flatMap(_.split(" ")).map((_,1))
    rdd2.saveAsSequenceFile("output/seq")
  }
}
