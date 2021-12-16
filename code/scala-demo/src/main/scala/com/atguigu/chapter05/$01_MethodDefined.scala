package com.atguigu.chapter05

object $01_MethodDefined {

  /**
    * 方法定义语法: def 方法名( 参数名:类型,.... ): 返回值类型 = { 方法体 }
    */
  def main(args: Array[String]): Unit = {

    println(add(10, 20))
  }

  def add(x:Int , y:Int):Int = {

    println("............")
    x+y
  }
}
