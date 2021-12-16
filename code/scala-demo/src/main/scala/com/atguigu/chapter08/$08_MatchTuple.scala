package com.atguigu.chapter08

object $08_MatchTuple {

  //匹配元组的时候,变量是几元元组,匹配条件就只能几元元组
  def main(args: Array[String]): Unit = {

    val t1:(Any,Any,Any) = ("lisi",20,"shenzhen")

    t1 match {

      case (x:String,y:Int,z) =>  println(s"x=${x} y=${y} z=${z}")
      //case (x,y) =>  println(s"x=${x} y=${y} z=${z}")
    }

    val list2 = List(
      ("宝安区",("宝安中学",("法师班",("安其拉",20)))),
      ("宝安区",("宝安中学",("法师班",("王昭君",20)))),
      ("宝安区",("宝安中学",("法师班",("甄姬",20)))),
      ("宝安区",("宝安中学",("法师班",("小乔",20))))
    )

    list2.foreach(x => {
      x match {
        case (regionName,(schoolName,(clazzName,(stuName,age)))) => println(stuName)
      }
    })
  }
}
