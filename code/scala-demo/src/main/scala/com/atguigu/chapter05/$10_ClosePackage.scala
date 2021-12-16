package com.atguigu.chapter05

object $10_ClosePackage {

  /**
    * 闭包: 函数体中使用了不属于函数本身的变量的情况称之为闭包
    */
  def main(args: Array[String]): Unit = {

    println(func(100))
  }

  val y = 10
  //闭包函数
  val func = (x:Int) => x+y
}
