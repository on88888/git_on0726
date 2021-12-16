package com.atguigu.day06

import org.junit.Test

class $02_ReadWrite {

  import org.apache.spark.{SparkConf, SparkContext}
  val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

  /**
   * 文件读取
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

    val rdd = sc.textFile("datas/wc.txt")
    //数据保存到文本文件中
    //rdd.saveAsTextFile("output/text")

    //保存到对象文件
    //rdd.saveAsObjectFile("output/obj")

    val rdd2 = rdd.flatMap(_.split(" ")).map((_,1))
    //保存到序列文件
    //rdd2.saveAsSequenceFile("output/seq")

    //rdd2.saveAs
  }
}
