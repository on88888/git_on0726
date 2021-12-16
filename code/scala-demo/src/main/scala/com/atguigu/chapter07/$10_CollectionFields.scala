package com.atguigu.chapter07

object $10_CollectionFields {

  def main(args: Array[String]): Unit = {

    val list = List(1,4,3,10)

    //判断集合中是否包含某个元素
    println(list.contains(100))

    //判断集合是否为空
    println(list.isEmpty)

    //获取集合长度
    println(list.size)
    println(list.length)

    //将集合所有元素转成字符串
    //println(list.toString())
    println(list.mkString("#"))

    //集合转迭代器
    // iterator: 只能使用一次的迭代器
    // Iterable: 可重复使用的迭代器
    val it = list.iterator
    while(it.hasNext){
      println(it.next())
    }
    println("-"*100)
    while(it.hasNext){
      println(it.next())
    }

    println("*"*100)
    val it2 = list.toIterable
    for(element<- it2){
      println(element)
    }

    println("+"*100)
    for(element<- it2){
      println(element)
    }
  }
}
