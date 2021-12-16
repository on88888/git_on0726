package com.atguigu.chapter11

object $03_LowUpperGeneric {

  class Animal

  class Pig extends Animal

  class BigPig extends Pig

  class miniPig extends BigPig

  def m1[T<:Pig](x:T): Unit ={
    println(x)
  }

  def m2[T>:BigPig](x:T) = {
    println(x)
  }

  /**
    * 上限[ T <: 类型 ]: 指代泛型的类型必须是指定类型或者是其子类
    * 下限[ T >: 类型 ]： 指代泛型的类型必须是指定类型或者是其父类
    */
  def main(args: Array[String]): Unit = {

    //m1[Animal](new Animal)

    val x:Any = new miniPig
    val y:Any = 10
    m2(y)
  }
}
