package test.c3

import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName Test
 * @createTime 2021年11月02日 21:21
 */
object Test {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    //1、读取数据
    val rdd1 = sc.textFile("datas/agent.log")

    //2、列裁剪[ 省份 广告id ]
    val rdd2 = rdd1.map( line=>{
//      val c: Char = line(0)
//      val c: Char = line.apply(2)
//      val str: String = line.split(" ").apply(1)
      val arr = line.split(" ")
      ( (arr(1), arr.last),1  ) //(省份,广告)
    } )

    println(rdd2.collect().toList)

  }
}
