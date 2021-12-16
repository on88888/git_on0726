package com.atguigu.chapter01

import java.io.File

import scala.io.{BufferedSource, Source}

/**
  * java中main的语法: public static void main(String[] args) {.....}
  * scala中没有public关键字,默认就是public的
  * scala中将静态与非静态做了区分,object中的方法/属性全部是类似java static修饰的,class中的方法/属性都是类似java非static修饰的
  * scala的main语法: def main(args: Array[String]): Unit = {.....}
  *     def: defined的缩写,def是方法标识符
  *     main： 方法名
  *     Array: 就是数组
  *     Array[String]: 字符串数组
  *     args： 变量名
  *     Unit： 返回值类型,Unit类似java的void
  */
object HelloWord {

  val name = "zhangsan"

  def main(args: Array[String]): Unit = {

    System.out.println("hello word....")
    //command execute failed
    //NoSuchXXX
    println("hello .....")

    //new Hello().main()

    println(HelloWord.name)
    import com.atguigu.chapter06.$01_ClassDefined._
    val person = new Person()
    //println(person.name)
  }
}

object ImplicitsTest{
  implicit def double2Int(x:Double):Int = x.toInt

  implicit def file2BufferedSource(file:File):BufferedSource = {

      Source.fromFile(file)

  }

  implicit val a:Int = 30
  implicit val b:Int = 40
}
