package com.atguigu.chapter04

object $02_If {

  /**
    * scala中判断语句的用法:
    *     1、单分支: if(..){...}
    *     2、双分支: if(..){...} else{...}
    *     3、多分支: if(..){...} else if(...){...} ... else{...}
    * scala的分支判断语句是返回值的，返回值就是符合条件的分支的块表达式的结果值
    *
    */
  def main(args: Array[String]): Unit = {

    val a = 10
    //单分支
    if(a%5==0){
      println("a是5的倍数")
    }
    //双分支
    if(a%3==0){
      println("a是3的倍数")
    }else{
      println("a是不是3的倍数")
    }

    //多分支
    val r = if(a%4==0){
      println("a是4的倍数")
      10
    }else if(a%6==0){
      println("a是6的倍数")
      20
    }else{

      if(a%2==0){
        println("a是2的倍数")
        30
      }else{
        println("a不是2的倍数")
        40
      }
    }

    println(r)

  }
}
