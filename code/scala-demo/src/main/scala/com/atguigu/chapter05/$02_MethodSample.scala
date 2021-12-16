package com.atguigu.chapter05

object $02_MethodSample {

  /**
    * 方法的简化原则:
    *     1、如果方法体只有一个语句,方法体的{}可以省略
    *     2、如果想要使用方法体的块表达式的结果值作为方法的返回值,那么方法的返回值类型可以省略,会根据方法体块表达式结果值推断
    *         注意: 如果方法体中有return关键字则必须定义方法的返回值类型
    *     3、如果方法不需要参数,那么在定义方法的时候参数列表的()可以省略
    *         注意:
    *             1、如果在定义方法的时候,参数列表的小括号省略了,则调用方法的时候,也不能带上()
    *             2、如果在定义方法的时候,参数列表的小括号没有省略,则调用方法的时候,()可有可无
    *     4、如果方法不需要返回值,在定义方法的时候=可以省略 [=与{}不能同时省略]
    *
    */
  def main(args: Array[String]): Unit = {

    println(add2(10, 20))
    println(add3(10, 20))
    //1、如果在定义方法的时候,参数列表的小括号省略了,则调用方法的时候,也不能带上()
    printHello2
    //2、如果在定义方法的时候,参数列表的小括号没有省略,则调用方法的时候,()可有可无
    printHello()
    printHello

    printHello3
  }

  //标准定义形式
  def add(x:Int,y:Int):Any = { x/y }

  //1、如果方法体只有一个语句,方法体的{}可以省略
  def add2(x:Int,y:Int):Int =  x+y

  //2、如果想要使用方法体的块表达式的结果值作为方法的返回值,那么方法的返回值类型可以省略,会根据方法体块表达式结果值推断
  def add3(x:Int,y:Int) = x+y

  //如果方法体中有return关键字则必须定义方法的返回值类型
  def add4(x:Int,y:Int):Int = {

    if(y==0) return 0
    return x/y
  }

  //标准形式
  def printHello():Unit = {

    println("Hello......")
  }

  //3、如果方法不需要参数,那么在定义方法的时候参数列表的()可以省略
  def printHello2 =  println("Hello......")

  //4、如果方法不需要返回值,在定义方法的时候=可以省略
  def printHello3 {
    println("Hello......")
  }
}
