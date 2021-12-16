package com.atguigu.chapter07

import scala.collection.immutable.Queue

object $16_ImmutableQueue {

  /**
    * 不可变队列的创建: immutable.Queue[元素类型](初始元素,....)
    *
    */
  def main(args: Array[String]): Unit = {

    val q1 = Queue[Int](1,3,2,6,8,10)
    println(q1)

    //添加元素
    val q2 = q1.+:(30)
    println(q2)

    val q3 = q1.:+(40)
    println(q3)

    val q4 = q1.++(List(10,30,20,60,40))
    println(q4)

    val q5 = q1.++:(List(10,30,20,60,40))
    println(q5)

    val q6 = q1.enqueue( 100 )
    println(q6)

    //删除元素
    println(q1.dequeue)

    //获取元素
    println(q1(0))

    //修改元素
    val q8 = q1.updated(0,100)
    println(q8)
  }
}
