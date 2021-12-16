package com.atguigu.chapter08

import scala.util.Random

object $04_MatchType {

  class Person
  /**
    * 匹配类型语法:
    *     变量 match {
    *         case x: 类型 => ....
    *         ....
    *     }
    *
    */
  def main(args: Array[String]): Unit = {

    val arr = Array("hadoop",10,false,2.2,new Person)

    val index = Random.nextInt( arr.length )

    println(s"index=${index}")

    arr(index) match {

      case _:String => println("String.....")
      case _:Int => println("Int.....")
      case x:Double => println(s"Double.....${x}")
      case x:Boolean => println("Boolean.....")
      case x => println("其他类型....")
    }
  }
}
