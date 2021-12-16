package com.atguigu.chapter05

object $09_Currying {

  /**
    * 柯里化: 有多个参数列表的方法称之为柯里化
    *
    */
  def main(args: Array[String]): Unit = {

    println(add(10, 20)(30)(40))

    val func = m1(10,20)

    val f1 = func(30)

    val r = f1(40)
    println(r)
    m1(10,20)(30)(40)
  }

  /**
    * 柯里化: 有多个参数列表的方法称之为柯里化
    */
  def add( x:Int,y:Int )(z:Int)(a:Int) = x+y+z+a

  /**
    * 柯里化演变过程
    */
  def m1(x:Int,y:Int) = {

    val func = (z:Int) => {

      val f1 = (a:Int) => {
          x+y+z+a
      }

      f1
    }

    func
  }
}
