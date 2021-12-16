package com.atguigu.chapter05

object $06_HightFunction {

  /**
    * 高阶函数: 以函数作为参数/返回值的方法/函数称之为高阶函数
    *
    *
    */
  def main(args: Array[String]): Unit = {

    val func = (x:Int,y:Int) => x-y

    println(add(10, 40, func))

    println(add(20, 30, m1 _ ))

  }

  def m1(x:Int,y:Int):Int = x+y

  /**
    * 高阶函数
    * @param x
    * @param y
    * @param func
    */
  def add(x:Int,y:Int, func: (Int,Int)=>Int  ) = {

    func(x,y)
  }
}
