package com.atguigu.chapter11

object $04_GenericChange {

  class Animal

  class Pig extends Animal

  //非变
  class Parent[T]

  //协变
  class Parent2[+T]

  //逆变
  class Parent3[-T]
  /**
    * 非变[T] : 类型一样泛型不一样的时候创建出来的对象之间没有任何关系
    * 协变[+T] : 类型一样泛型是父子关系的时候创建出来的对象之间继承了泛型的父子关系
    * 逆变[-T] : 类型一样泛型是父子关系的时候创建出来的对象之间颠倒了泛型的父子关系
    * @param args
    */
  def main(args: Array[String]): Unit = {

    var list1 = List[Animal](new Animal,new Animal)
    var list2 = List[Pig](new Pig,new Pig)
    list1 = list2
    println(list1)

    //非变
    var p1 = new Parent[Animal]
    var p2 = new Parent[Pig]
    //p1 = p2
    //p2 = p1

    //协变
    var p3 = new Parent2[Animal]
    var p4 = new Parent2[Pig]
    p3 = p4
    //p4 = p3

    //逆变
    var p5 = new Parent3[Animal]
    var p6 = new Parent3[Pig]

    p6 = p5

    //p5 = p6
  }
}
