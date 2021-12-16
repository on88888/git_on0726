package com.atguigu.chapter07

import scala.io.Source

object $07_Tuple {

  class Region(name:String,school:School)

  class School(name:String,clazz:Clazz)

  class  Clazz(name:String, stu:Student)

  class Student(name:String,age:Int)
  /**
    * 元组的创建:
    *       1、通过()创建:  (初始元素,....)
    *       2、通过->方式[只适用于创建二元元组]: K -> V
    *       scala中二元元组表示KV键值对
    * 元组最多只能存放22个元素
    * 元组一旦定义,元素和长度都不可以改变
    * 元组获取元素: 元组名._N  [N是元素的角标,元组的角标从1开始]
    *
    */
  def main(args: Array[String]): Unit = {

    //1、通过()创建:  (初始元素,....)
    val t1 = ("lisi",20,"shenzhen")

    //2、通过->方式[只适用于创建二元元组]: K -> V
    val t2 = "zhangsan" -> 100

    println(t1)
    println(t2)

    println(t1._3)

    val lines = Source.fromFile("datas/log.txt").getLines()

    val logs = for(line<- lines) yield{
      val arr = line.split("\t")

      ( arr(0) , arr(1), arr(2).toInt , arr(3) )
    }


    val list = List(
      new Region("宝安区",new School("宝安中学",new Clazz("法师班",new Student("安其拉",20)))),
      new Region("宝安区",new School("宝安中学",new Clazz("法师班",new Student("王昭君",18)))),
      new Region("宝安区",new School("宝安中学",new Clazz("法师班",new Student("甄姬",16)))),
      new Region("宝安区",new School("宝安中学",new Clazz("法师班",new Student("小乔",22))))
    )

    val list2 = List(
      ("宝安区",("宝安中学",("法师班",("安其拉",20)))),
      ("宝安区",("宝安中学",("法师班",("王昭君",20)))),
      ("宝安区",("宝安中学",("法师班",("甄姬",20)))),
      ("宝安区",("宝安中学",("法师班",("小乔",20))))
    )

    for( element<- list2 ) {
      println( element._2._2._2._1  )
    }

  }
}
