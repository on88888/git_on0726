package com.atguigu.chapter08

object $09_ParamForMatch {

  def main(args: Array[String]): Unit = {

    //变量声明模式匹配
    val t1 = ("zhangsan",20,"shenzhen")

    println(t1._1)

    val (name,age,_) = ("zhangsan",20,"shenzhen")
/*    ("zhangsan",20,"shenzhen") match {
      case (name,age,address) =>
    }*/
    println(name)

    val Array(x,_) = Array(10,2)
/*    Array(10,2) match {
      case Array(x) =>
    }*/

    val y :: tail = List(10,20)
    println(y,tail)

    val map = Map("aa"->10,"bb"->20)
    for((k,v) <- map ){
      println(k)
      println(v)
    }

  }
}
