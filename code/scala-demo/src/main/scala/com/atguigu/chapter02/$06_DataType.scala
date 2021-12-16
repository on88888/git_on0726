package com.atguigu.chapter02

object $06_DataType {

  /**
    * java的数据类型
    *     基本数据类型： byte、short、int、long、float、double、char、boolean
    *     引用数据类型: String，数组、集合
    *
    * scala数据类型
    * Any: scala所有类的父类
    *    AnyVal: 值类型
    *       Byte、Short、Int、Float、Double、Long、Char、Boolean
    *       Unit: 相当于java的void, 有一个对象()
    *       stringops: 代表一系列对字符串的扩展类
    *    AnyRef: 引用类型
    *       String,java class/数组/集合,scala class/数组/集合
    *           Null: 是所有引用类型的子类, 有一个实例null,一般用于给引用类型赋予初始值,在使用null给引用类型赋予初始值的时候必须指定变量的类型
    * Nothing: 所有类的子类
    */
  def main(args: Array[String]): Unit = {

    var name:String = null

    name = "lisi"


    //val a:Int = null
    //println(a)
    //val xx = new Nothing
    //println(xx)
  }
}
