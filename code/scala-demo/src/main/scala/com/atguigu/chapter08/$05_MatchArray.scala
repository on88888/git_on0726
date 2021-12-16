package com.atguigu.chapter08

object $05_MatchArray {

  /**
    * 匹配数组
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val arr: Array[Int] = Array(1,3,2)

    arr match {

      case Array(_) => println("数组只有一个元素")
      //case Array(x:String,y:Int,z) => println("数组有三个元素,第一个元素是Int类型,第二个元素是Int类型")
      case Array(x:Int,y:Int,z) => println("数组有三个元素,第一个元素是Int类型,第二个元素是Int类型")
      case Array(x,y,z) => println("数组有三个元素")
      case Array(x,y,_*) => println("数组最少有两个元素")
    }
  }
}
