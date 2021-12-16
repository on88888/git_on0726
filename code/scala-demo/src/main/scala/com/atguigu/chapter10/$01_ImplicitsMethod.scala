package com.atguigu.chapter10

import java.io.File

import scala.io.{BufferedSource, Source}
import com.atguigu.chapter01.ImplicitsTest._


class ImplicitsClassTest{
  implicit def double2Int(x:Double):Int = x.toInt

  implicit def file2BufferedSource(file:File):BufferedSource = Source.fromFile(file)
}


object $01_Implicits {

  /**
    * 隐式转换分为三种:
    *       隐式转换方法: 悄悄的将一个类型转成另一个类型
    *           语法: implicit def 方法名( 参数名: 待转换类型 ): 目标类型 = {....}
    *           隐式转换方法的使用时机:
    *               1、当前类型与目标类型不一致的时候,scala会自动调用隐式转换方法
    *               2、当对象使用了不属于自身的属性/方法的时候,scala会自动调用隐式转换方法
    *       隐式参数
    *       隐式类
    *隐式转换的解析机制:
    *     1、首先从当前作用域和父作用域中查询是否有符合要求的隐式转换,如果有则直接调用,如果没有则报错
    *     2、如果隐式转换定义在其他的object/class中,想要使用需要导入
    *           1、如果隐式转换定义在object中, 可以通过 import object名称.隐式转换名称/import object名称._ 导入
    *           2、如果隐式转换定义class中, 可以通过 import 对象名.隐式转换名称/import 对象名._ 导入
    *
    */
  def main(args: Array[String]): Unit = {

    //导入隐式转换[从object导入]
    //import ImplicitsTest.double2Int
    //import ImplicitsTest._

    //val obj = new ImplicitsClassTest
    //import obj._
    //import obj.double2Int
    val d1:Int = 2.0
    val d2:Int = 2.0
    val d3:Int = 2.0
    val d4:Int = 2.0
    val d5:Int = 2.0
    val d6:Int = 2.0
    val d7:Int = 2.0
    val d8:Int = 2.0
    val d9:Int = 2.0
    val a1:Int = 2.0
    val a2:Int = 2.0

    val file = new File("C:\\Users\\admin\\Desktop\\wc.txt")

    file.getLines.foreach(println)
    //Source.fromFile().getLines()
  }


}
