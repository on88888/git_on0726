package com.atguigu.chapter02

object $07_DataCover {

  /**
    * 数字和数字的转换
    *   1、低精度转高精度[Int->Long]: 自动转换
    *   2、高精度转低精度[Long->int]: toXXX方法转换
    * 数字和字符串的转换
    *   1、数字转字符串: 拼接/toString等方法
    *   2、字符串转数字: toXXX方法
    */
  def main(args: Array[String]): Unit = {
    //1、低精度转高精度[int->long]: 自动转换
    val a:Int = 100
    val b:Long = a

    //2、高精度转低精度[long->int]
    val c:Long = 100L
    val d:Int = c.toInt

    //1、数字转字符串: 拼接/toString等方法
    val s:String = s"${c}"
    val s2:String = c+""
    val s3:String = c.toString

    //2、字符串转数字:
    val s4:String = "10.0"
    val e:Double = s4.toDouble
    println(e)
  }
}
