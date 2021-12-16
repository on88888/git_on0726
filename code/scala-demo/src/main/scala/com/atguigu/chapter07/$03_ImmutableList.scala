package com.atguigu.chapter07

object $03_ImmutableList {

  /**
    * 不可变List创建:
    *     1、通过apply方法创建: List[元素类型](初始元素,....)
    *     2、通过 :: 方法创建:  初始元素 :: 初始元素 :: ... :: Nil/不可变List  或者 list.::(初始元素)
    *            :: 最右边必须是Nil或者是不可变List
    *           Nil其实可以当做一个空的不可变List, Nil一般用作给不可变List变量赋予初始值,Nil与不可变List的关系类似Null与String
    *           Nil给不可变List变量赋予初始值的时候,变量类型必须指定。
    * 添加元素方法:
    *    :: 是添加单个元素[类似之前不带=的一个+的方法]
    *    ::: 是添加一个集合所有元素[类似之前不带=的两个的方法]
    *
    */
  def main(args: Array[String]): Unit = {


    //通过apply方法创建: List[元素类型](初始元素,....)
    val list1 = List[Int](10,2,5,4,3)

    //通过 :: 方法创建:
    val list2 = 2 :: 3 :: 4 :: 5 :: Nil

    println(list1)
    println(list2)
    println(Nil)

    //var list3 = Nil

    //list3 = list2

    //添加元素
    val list3 = list2.+:(10)
    println(list3)

    val list4 = list2.:+(20)
    println(list4)

    val list5 = list2.::(30)
    println(list5)

    val list6 = list2.++(List(100,33,22))
    println(list6)

    val list7 = list2.++:(Array(44,55,66))
    println(list7)

    val list8 = list2.:::(List(66,77,88))
    println(list8)

    //获取角标元素
    println(list8(0))

    //修改指定角标元素
    val list9 = list8.updated(0,666)
    println(list8)
    println(list9)

    //不可变List转可变
    println(list9.toBuffer)
  }
}
