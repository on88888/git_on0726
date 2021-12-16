package com.atguigu.chapter07

import scala.collection.mutable

object $17_MutableQueue {

  def main(args: Array[String]): Unit = {

    //创建方式: mutable.Queue[元素类型](初始元素,..)
    val q1 = mutable.Queue[Int](1,3,2,6,5)

    //添加元素
    val q2 = q1.+:(10)

    val q3 = q1.:+(20)

    q1.+=(30)

    q1.+=:(40)

    println(q2)
    println(q3)
    println(q1)

    val q4 = q1.++(List(100,22,55,33))
    println(q4)

    val q5 = q1.++:(List(90,40,30,20,10))
    println(q5)


    q1.++=(List(90,40,30,20,10))

    println(q4)
    println(q5)
    println(q1)

    q1.enqueue(500,300)
    println(q1)

    //删除
    println(q1.dequeue())
    println(q1)

    //获取元素
    println(q1(0))

    //修改元素
    q1(0)=100
    println(q1)
  }
}
