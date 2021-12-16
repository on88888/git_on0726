package com.atguigu.day05

object $04_RDDLineage {

  /**
    * 血统: 一个job中多个rdd之间的关系
    *     rdd的血统可以通过toDebugString查看
    */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    val rdd1 = sc.textFile("datas/wc.txt")
    println("+"*100)
    println(rdd1.toDebugString)
    println("-"*100)
    val rdd2 = rdd1.flatMap(_.split(" "))
    println(rdd2.toDebugString)
    println("-"*100)
    val rdd3 = rdd2.map((_,1))
    println(rdd3.toDebugString)
    println("-"*100)
    val rdd4 = rdd3.reduceByKey(_+_)
    println(rdd4.toDebugString)
    println("+"*100)
    println(rdd4.collect().toList)

//    println(rdd3.countByKey())
  }
}
