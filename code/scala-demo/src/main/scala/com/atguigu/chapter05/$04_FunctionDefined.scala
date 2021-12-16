package com.atguigu.chapter05

object $04_FunctionDefined {

  /**
    * 函数的定义语法:  val 函数名 = (参数名:类型,...) => { 函数体 }
    *   函数的返回值默认就是函数体块表达式的结果值
    *   函数是对象,函数的类型: (参数类型,...) => 返回值类型
    *
    * 函数的简化: 如果函数体的{}中只有一个语句,{}可以省略
    *
    *
    */
  def main(args: Array[String]): Unit = {

    println(func(10, 20))

    println(m1)

    println(func2(10, 20))

    val x = func
    println(x(10, 20))
  }

  val func: (Int, Int) => Int = (x:Int, y:Int) => x+y

  val m1 = () => println("Hello.....")

  val func2: (Int, Int) => Int  = new Function2[Int,Int,Int] {
    override def apply(v1: Int, v2: Int): Int = v1+v2
  }

}
