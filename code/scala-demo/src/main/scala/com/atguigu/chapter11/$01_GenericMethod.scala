package com.atguigu.chapter11

object $01_GenericMethod {

  /**
    * 泛型方法
    *     语法: def 方法名[T,U,..]( 参数名:T ,...):U = {....}
    */
  def main(args: Array[String]): Unit = {

    m1(Array(1,2,3))
    m1(Array("xx","yy"))
  }

  def m1[T](arr:Array[T]) = println(arr.size)
}
