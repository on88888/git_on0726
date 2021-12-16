package com.atguigu.chapter04

object $04_WhileAndDoWhile {

  /**
    * scala的while与do-while的用法与java完全一样
    */
  def main(args: Array[String]): Unit = {

    var a = 11

    while(a<=10){
      println(s"a=${a}")
      a=a+1
    }

    var b = 11
    do{
      println(s"b=${b}")
      b=b+1
    }while(b<=10)
  }
}
