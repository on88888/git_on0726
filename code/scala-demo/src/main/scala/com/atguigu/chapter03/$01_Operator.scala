package com.atguigu.chapter03

import com.atguigu.chapter01.Hello

object $01_Operator {

  /**
    * java的运算符:
    *     算术运算符: + - * / ++ -- %
    *     位运算符: << >> >>> & |
    *     逻辑运算符: && || !
    *     比较运算符: > < >= <= == !=
    *     三元运算符: 布尔表达式 ? ... : ....
    *     赋值运算符: += -= *= /= =
    *scala中没有++、--、三元运算符
    *     算术运算符: + - * /  %
    *     位运算符: << >> >>> & |
    *     逻辑运算符: && || !
    *     比较运算符: > < >= <= == !=
    *     赋值运算符: += -= *= /= =
    *scala中的运算符是一个个的方法, scala中方法调用的两种形式:
    *       1、对象.方法名 (参数值,....)
    *       2、对象 方法名 (参数值,....) [如果只有一个参数,()可以省略]
    */
  def main(args: Array[String]): Unit = {

    val a = 10
    val b = 20
    println( a + b)
    println( a - b)
    println( a * b)
    println( a / b)

    val e = a+b
    val d = a.+(20)
    val d2 = a + 20
    println(d)
    println(d2)
    10+20

    val hello = new Hello

    //hello.main(null)
    //hello main null
  }
}
