package com.atguigu.chapter07

object $11_Collection {

  def main(args: Array[String]): Unit = {

    val list = List(1,3,2,7,2,9,10,1)

    //去重: distinct  ********
    val list2 = list.distinct
    println(list2)

    //获取除开前N个元素的其他所有元素
    println(list.drop(3))

    //获取除开后N个元素的其他所有元素
    println(list.dropRight(3))

    //获取除开最后一个元素的所有元素
    println(list.init)

    //获取除开第一个元素的其他所有元素 ********
    println(list.tail)

    //获取第一个元素 ********
    println(list.head)

    //获取最后一个元素 ********
    println(list.last)

    //反转 ****
    println(list.reverse)

    //获取指定角标范围的所有元素 [包含start角标,不包含stop角标元素] ********
    println(list.slice(2, 5))

    //滑窗(size[,step]) ********
    //size: 窗口长度
    //step: 滑动长度
    println(list.sliding(2).toList)

    //获取前N个元素 ********
    println(list.take(3))

    //获取后N个元素
    println(list.takeRight(3))

    //交集【两个集合共同的元素】
    val list3 = List(1,2,3,4,5)
    val list4 = List(4,5,6,7,8)
    println(list3.intersect(list4))
    //差集[A差B的结果就是A中有B中没有的元素]
    println(list3.diff(list4))
    //并集
    println(list3.union(list4))

    //拉链
    val list5 = List("zhangsna","lisi","wangwu","zhaoliu")
    val list6 = List(20,25,43)

    val list7 = list5.zip(list6)
    println(list7)

    //反拉链
    println(list7.unzip)

    //将元素与角标拉链
    println(list5.zipWithIndex)
  }
}
