package com.atguigu.chapter08

object $10_PartialFunction {

  /**
    * 偏函数: 没有match关键字的模式匹配称之为偏函数
    *     语法:  val 函数名:PartailFunction[IN,OUT] = {
    *        case 条件=>...
    *        case 条件=>...
    *        case 条件=>...
    *        .....
    *     }
    *     IN: 代表函数参数类型
    *     OUT: 代表函数返回值类型
    *
    */
  def main(args: Array[String]): Unit = {

    val list2 = List(
      ("宝安区",("宝安中学",("法师班",("安其拉",20)))),
      ("宝安区",("宝安中学",("法师班",("王昭君",20)))),
      ("宝安区",("宝安中学",("法师班",("甄姬",20)))),
      ("宝安区",("宝安中学",("法师班",("小乔",20))))
    )

    val t = ("宝安区",("宝安中学",("法师班",("安其拉",20))))

    t match {

      case (regionName,(schoolName,(clazzName,(stuName,age)))) => println(stuName)
    }

//    val func:PartialFunction[(String,(String,(String,(String,Int)))) , Unit] = {
//      case  (regionName,(schoolName,(clazzName,(stuName,age)))) => println(stuName)
//    }

    val func: ( (String,(String,(String,(String,Int)))) ) => Unit = {
      case  (regionName,(schoolName,(clazzName,(stuName,age)))) => println(stuName)
    }

    func( t )

    //偏函数在工作中使用的正确姿势
    list2.foreach( func )
    //直接传递函数值
    list2.foreach( {
      case  (regionName,(schoolName,(clazzName,(stuName,age)))) => println(stuName)
    } )
    //方法只有一个参数,()可以省略
    list2.foreach {
      case  (regionName,(schoolName,(clazzName,(stuName,age)))) => println(stuName)
    }

  }
}
