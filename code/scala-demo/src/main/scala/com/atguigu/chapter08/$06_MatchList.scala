package com.atguigu.chapter08

object $06_MatchList {

  def main(args: Array[String]): Unit = {

    val list = List(1,4,2)

    list match {

      case List(x) => println("list只有一个元素")
      case List(x:Int,y,z) => println("list有三个元素,第一个元素类型是INt")
      case List(x,y,z) => println("list有三个元素")
      case List(x,y,z,_*) => println("list最少有三个元素")
    }

    list match {

      case x :: Nil => println("list只有一个元素")
      case (x:Int)  :: y :: z :: Nil => println("list有三个元素,第一个元素类型是INt")
      case x  :: y :: z :: Nil => println("list有三个元素")
      case x  :: y :: z :: xxx => println(s"list最少有三个元素 ${xxx}")
    }
  }
}
