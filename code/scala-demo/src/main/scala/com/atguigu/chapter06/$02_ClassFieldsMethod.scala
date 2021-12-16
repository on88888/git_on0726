package com.atguigu.chapter06

object $02_ClassFieldsMethod {

  class Person{

    //定义属性
    val name:String = "lisi"

    var age:Int = 20

    private val address:String = "shenzhen"
    //scala的var修饰的属性可以使用_赋予初始值,使用_赋予初始值的时候必须定义属性的类型
    var sex:String = _

    //val x:String = _

  /*  private */def m1(x:Int,y:Int) = x+y
  }

  /**
    * java定义属性: [访问修饰符] 类型 变量名 [= 值]
    *
    * scala定义属性: [访问修饰符] val/var 变量名:类型 = 值
    */
  def main(args: Array[String]): Unit = {

    val person = new Person

    println(person.name)
    println(person.age)
    //person.name = "lisi"
    person.age = 50
    println(person.age)
    //println(person.address)

    println(person.sex)

    //var xx:Int = _

    println(person.m1(10, 20))
  }
}
