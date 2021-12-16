package com.atguigu.chapter07

import scala.collection.mutable

object $09_MutableMap {

  /**
    * 可变Map的创建: mutable.Map[K的类型,V的类型]( K->V , (K,V),... )
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val map = mutable.Map[String,Int]("aa"->10,"cd"->20,"ad"->40)
    println(map)

    //添加元素
    val map2 = map.+( "ee"->50 )
    println(map2)

    map.+=( "ff"->60 )
    println(map)

    val map3 = map.++(List("tt"->20,"pp"->30))

    println(map3)
    map.++=(List("oo"->30,"gg"->40))
    println(map)

    map.put("rr",100)
    println(map)
    //删除
    val map5 = map.-("oo")
    println(map5)

    map.-=("gg")
    println(map)

    val map6 = map.--(List("rr","cd"))
    println(map6)

    map.--=(List("aa","cd","ad"))
    println(map)

    map.remove("oo")
    println(map)

    //获取元素
    //根据key获取value值
    println(map.getOrElse("ff", -1))

    //获取所有的key
    println(map.keys)
    //获取value
    println(map.values)

    //修改value值
    map("rr1")=2000
    println(map)

    map.put("gg",500)
    println(map)
  }
}
