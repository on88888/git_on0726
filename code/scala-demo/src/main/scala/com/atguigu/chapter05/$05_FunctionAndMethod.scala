package com.atguigu.chapter05

object $05_FunctionAndMethod {

  /**
    * 函数与方法的区别:
    *     1、方法如果定义在class/object中可以重载的,函数是对象,函数不可以重载的
    *     2、方法存储在方法区中,函数是对象,存储在堆中
    * 函数与方法的联系:
    *     1、方法如果定义在方法中,此时方法就是函数,不能重载
    *     2、方法手动转成函数: 方法名 _
    */
  def main(args: Array[String]): Unit = {
    def add2(x:Int,y:Int) = x+y
    //def add2(x:Int,y:Int,z:Int) = x+y
    println(add2(10, 20))

    val func2 = add3 _
  }

  val func = (x:Int,y:Int)=> x+y
  //val func = (x:Int,y:Int,z:Int)=> x+y+z

  def add(x:Int,y:Int) = x+y

  def add(x:Int,y:Int,z:Int) = x+y+z

  def add3(x:Int,y:Int,z:Int) = x+y+z
}
