package com.atguigu.chapter06

object $01_ClassDefined {

  class Person{

    private[chapter06] val name:String = "lisi"
  }

  /**
    * java中创建类: [修饰符] class 类名{....}
    * java中创建对象: new 类名(...)
    *
    * scala中创建类: class 类名{...}
    * scala中创建对象: new 类名(...)
    */
  def main(args: Array[String]): Unit = {
    val person = new Person
    println(person)

    println(person.name)

   // println(XXXXXXXXX)
    //AAAAAAAAA()
  }
}
