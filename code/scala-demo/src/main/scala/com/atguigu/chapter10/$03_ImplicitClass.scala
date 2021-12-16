package com.atguigu.chapter10

import java.io.File

object $03_ImplicitClass {

  implicit class RichFile(file:File){

    def m1(x:Int,y:Int) = x+y
  }

  /**
    * 隐式类: 悄悄的将一个类型转成隐式类的类型
    *     语法: implicit class 类名(属性名: 待转换类型){.....}
    *  隐式类必须定义在object/class中,不能置于最顶层
    *
    */
  def main(args: Array[String]): Unit = {

    val file = new File("C:\\Users\\admin\\Desktop\\wc.txt")

    file.m1(10,20)
  }


  //implicit def file2RichFile( file:File ):RichFile = new RichFile(file)
}
