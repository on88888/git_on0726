package com.atguigu.chapter08

import scala.io.StdIn

object $01_Match {

  /**
    * 模式匹配语法:
    *     变量 match {
    *        case 条件1 => ....
    *        case 条件2 => ....
    *        case 条件3 => ....
    *        .....
    *     }
    * 模式匹配有返回值,返回值就是符合条件的分支的块表达式的结果值
    * 模式匹配最后一个条件一般使用 case _ => 指代其他情况
    */
  def main(args: Array[String]): Unit = {

    val wc = StdIn.readLine("请输入一个单词:")

    val r = wc match {
      case "hadoop"  => {
        println("输入的是hadoop")
        10
      }
      case "spark" => {
        println("输入的是spark")
        20
      }
      case "flume" =>
        val a = 10
        val b = 20
        println(a+b)
        println("输入的是flume")
        a+b

      //相当于switch的default语句
      /*case x => {
        println("其他....")
      }*/
        //如果条件变量在=>右边不使用可以用_代替
      case _ => {
        println("其他....")
        -1
      }
    }

    println(r)
  }
}
