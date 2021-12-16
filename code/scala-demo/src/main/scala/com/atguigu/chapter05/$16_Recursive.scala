package com.atguigu.chapter05

object $16_Recursive {

  /**
    * 递归: 自己调用自己
    *     前提:
    *         1、必须要有退出条件
    *         2、必须定义返回值类型
    * 递归函数必须定义函数类型,递归函数不能写在方法里面
    */
  def main(args: Array[String]): Unit = {

    println(m1(5))


    println(func(5))
  }
  val func: Int=>Int = (n:Int) => {
    if(n==1) 1
    else n * func(n-1)
  }


  val func2:Int=>Int = new Function1[Int,Int]{
    override def apply(n: Int): Int = {
      if(n==1) 1
      else n * func2(n-1)
    }

  }

  /**
    * 递归方法
    * @param n
    * @return
    */
  def m1(n:Int):Int= {

    if(n==1) 1
    else n * m1(n-1)

  }
}
