package com.atguigu.chapter07

import scala.collection.mutable

object $06_MutableSet {

  /**
    * 可变Set创建: mutable.Set[元素类型](初始元素,....)
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val set = mutable.Set[Int](10,3,2,6,7)

    println(set)

    //添加元素
    val set2 = set.+(20)

    set.+=(30)

    println(set2)
    println(set)

    val set3 = set.++(List(33,11,55,44))
    val set4 = set.++:(List(33,11,55,44))
    println(set3)
    println(set4)

    set.++=(List(77,888,33))
    println(set)

    set.add(100)
    println(set)

    //删除元素
    val set6 = set.-(2)
    println(set6)

    set.-=(3)
    println(set)

    val set7 = set.--(List(6,7,77))
    println(set7)
    val set8 = set.--=(List(33,30,2))
    println(set8)
    println(set)
    println(set.eq(set8))

    set.remove(888)
    println(set)

    set.update(6,false)
    println(set)
  }
}
