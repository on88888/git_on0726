package com.atguigu.chapter04

object $01_Block {

  /**
    * 块表达式: 由{}包裹的一段代码称之为块表达式，有返回值,返回值是{}中最后一个表达式的结果值。
    *
    */
  def main(args: Array[String]): Unit = {

    val r = {
      println("Hello.....")
      val a = 10;
      val b = 20
      a+b
    }

    println( r )
    10
  }
}
