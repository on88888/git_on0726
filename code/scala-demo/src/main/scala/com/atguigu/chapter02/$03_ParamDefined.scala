package com.atguigu.chapter02

object $03_ParamDefined {

  /**
    * java的变量定义语法:[final] [static]  类型 变量名 = 值
    * scala的变量定义语法: val/var 变量名:类型 = 值
    *     val与var的区别:
    *       val修饰的变量类似java final修饰的,变量的值不可改变
    *       var修饰的变量类似java 非final修饰的,变量的值可以改变
    * scala在定义变量的时候,变量可以省略,scala会自动推断变量类型：  val/var 变量名 = 值
    * scala在定义变量的时候必须初始化
    * scala中如果一行只有一个语句,每行后面的分号可以省略
    *
    */
  def main(args: Array[String]): Unit = {

    val name:String = "zhangsan"

    var age:Int = 20



    println(name)
    println(age)
    //val修饰的变量类似java final修饰的,变量的值不可改变
    //name = "lisi"
    //var修饰的变量类似java 非final修饰的,变量的值可以改变
    age = 100
    println(age)

    //scala在定义变量的时候,变量可以省略,scala会自动推断变量类型
    val address = "beijing"
    println(address)

    //scala在定义变量的时候必须初始化
    //val sex:String

  }
}
