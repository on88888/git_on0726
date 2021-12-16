package com.atguigu.chapter07

import scala.collection.mutable.ListBuffer

object $04_MutableList {

  /**
    * 可变List创建
    *     1、通过apply方法: ListBuffer[元素类型](初始元素,....)
    *     2、通过new方式: new ListBuffer[元素类型]()
    *
    */
  def main(args: Array[String]): Unit = {

    //通过apply方法: ListBuffer[元素类型](初始元素,....)
    val list1 = ListBuffer[Int](10,3,2,6,7)

    val list2 = new ListBuffer[Int]()

    println(list1)
    println(list2)

    //添加元素
    val list3 = list1.:+(20)
    println(list3)

    val list4 = list1.+:(30)
    println(list4)

    list1.+=(40)
    println(list1)

    list1.+=:(50)
    println(list1)

    list1.insert(2,60,70)
    println(list1)

    val list5 = list1.++(List(100,22,33))
    println(list5)

    val list6 = list1.++:(List(11,44,77))
    println(list6)

    list1.++=(List(444,555,666))
    println(list1)

    list1.++=:(List(777,888,999))
    println(list1)

    //删除
    val list7 = list1.-(10)
    println(list7)

    list1.-=(999)
    println(list1)

    val list8 = list1.--(List(3,2,6,7,10))
    println(list8)

    list1.--=(List(3,2,6,7,10))
    println(list1)

    list1.remove(2,4)
    println(list1)

    //获取指定角标元素
    println(list1(0))

    //修改指定角标元素
    list1(0)=100
    println(list1)

    //可变List转不可变List
    println(list1.toList)
  }
}
