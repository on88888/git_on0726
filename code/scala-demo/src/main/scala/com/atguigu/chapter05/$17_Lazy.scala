package com.atguigu.chapter05

object $17_Lazy {

  /**
    * 惰性求值
    *     语法: lazy val 变量名 = 值
    *     lazy标识的变量只有在使用的时候才会初始化,没有使用之前不会初始化
    *
    */
  def main(args: Array[String]): Unit = {

    val name = "lisi"

    println(name)

    lazy val age = 20

    println(age)
    println("---------------")
  }
}
