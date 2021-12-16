package com.atguigu.chapter08

object $07_MatchObject {

  case class Person(val name:String,var age:Int, address:String)

  abstract class Sex

  case object Man extends Sex

  case object Woman extends Sex

  def m1(sex:Sex) = {
    println(sex)
  }
  /**
    * 样例类: 其实就是伴生类和伴生对象的封装
    *     语法: case class 类名([val/var] 属性名:类型,....)
    *     创建伴生类对象: 类名(属性值,...)
    *     样例类中属性使用val/var修饰与不使用val/var修饰的区别:
    *         属性如果没有使用val/var修饰,默认就是val修饰的。
    * 样例对象:
    *     语法: case object object名称
    *
    * 样例类可以直接用于模式匹配,普通类默认不可以直接用于模式匹配,如果想要让普通类也能直接用于模式匹配,需要在伴生对象中定义unapply方法
    *
    */
  def main(args: Array[String]): Unit = {

    val person = Person("zhangsan",20,"shenzhen")
    println(person)
    //person.name = "xx"

    //person.address = "beijing"


    m1(Man)

    person match {
      case Person(x,y,z) => println(s"x=${x} y=${y} z=${z}")
    }

    val stu = Student("lisi",30,"beijing")

    stu match {
      case Student(x,y,z) => println(s"x=${x} y=${y} z=${z}")
    }
  }
}

class Student(val name:String,var age:Int, val address:String)
object Student{

  def apply(name:String,age:Int,address:String) = new Student(name,age,address)

  def unapply(arg: Student): Option[(String, Int, String)] = {
    if(arg==null)
      None
    else
      Some( (arg.name,arg.age,arg.address)  )
  }
}