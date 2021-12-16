package com.atguigu.chapter07

object $05_ImmutableSet {

  /**
    * 不可变Set的创建: immutable.Set[元素类型](初始元素,....)
    * set的特性:无序，不重复
    */
  def main(args: Array[String]): Unit = {

    //不可变Set的创建: immutable.Set[元素类型](初始元素,....)
    val set = Set[Int](10,10,2,4,7,6,2)

    println(set)


    //添加元素
    val set2 = set.+(50)
    println(set2)

    val set3 = set.++(List(100,300,200,33))
    println(set3)

    val set4 = set.++:(List(100,300,200,33))
    println(set4)

    //删除元素
    val set5 = set.-(2)

    println(set5)

    val list6 = set.--(List(7,4))
    println(list6)


  }
}
