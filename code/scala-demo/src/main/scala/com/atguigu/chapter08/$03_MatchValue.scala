package com.atguigu.chapter08

import scala.io.StdIn

object $03_MatchValue {

  /**
    * 匹配值语法:
    *     变量 match {
    *         case 值1 => ...
    *         case 值2 => ...
    *         .....
    *     }
    * 在匹配值的时候,如果想要使用外部变量代替case条件,此时变量名首字母必须大写
    *
    */
  def main(args: Array[String]): Unit = {

    val wc = StdIn.readLine("请输入一个单词:")

    val X11 = "kafka"
    wc match {

      case "hadoop" => println("输入的是hadoop")
      case "spark" => println("输入的是spark")
      case "flume" => println("输入的是flume")
      case X11 => println("输入的是kafka....")
      case a => println("其他....")
    }
  }
}
