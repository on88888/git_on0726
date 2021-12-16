package com.atguigu.chapter11

object $02_GenericClass {

  class Person[T,U](val name:T,val age:U){

    var address:T = _

    def setAddress(x:T) = this.address=x

    def getAddress():T = this.address
  }

  /**
    * 泛型类
    *     语法:
    *           class 类名[T,U,..](属性名:T,..){
    *               def 方法名(参数名:U):W = {...}
    *           }
    *
    */
  def main(args: Array[String]): Unit = {

    val person = new Person[String,Int]("zhangsan",20)

    person.setAddress("SHENZHEN")
    println(person.name)
    println(person.age)
    println(person.getAddress())
  }
}
