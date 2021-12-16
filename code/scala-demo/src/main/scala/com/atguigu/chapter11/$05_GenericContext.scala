package com.atguigu.chapter11

import scala.beans.BeanProperty

object $05_GenericContext {

  class Person[T]{
    @BeanProperty var name:T = _
  }

  /**
    * 上下文:  会自动生成一个隐式参数
    *     T:类型
    * @param args
    */
  def main(args: Array[String]): Unit = {

    implicit val p:Person[String] = new Person
    val person = m1[String]("zhangsan")
    println(person.getName)

    val p2 = m2[String]("wangwu")
    println(p2.getName)
  }

  def m1[U](name:U)(implicit person:Person[U]) ={
    person.setName(name)
    person
  }

  def m2[U:Person](name:U) = {
    //召唤
    val person = implicitly[Person[U]]
    person.setName(name)
    person
  }
}
