package com.atguigu.day05

import org.apache.spark.rdd.RDD

object $02_ClosePackage {

  /**
    * 闭包: 函数体中使用了外部变量的函数称之为闭包.
    *
    * spark序列化原因： ********
    *       spark算子里面的函数是在executor中的task执行的,spark算子外面的代码是在driver执行的,如果spark算子里面的函数体中使用了dirver定义的变量
    *       此时spark会将该driver变量序列化之后传给task使用,所以就必须要求该driver变量类型必须能够序列化才行。
    * spark里面的有两种序列化: java序列化[默认使用],kryo序列化
    *       java序列化: 在序列化对象的时候会将对象的全类名、属性、属性类型、继承信息等全部都会序列化进去
    *       kryo序列化: 只会序列化类名、属性名、属性值、属性类型信息[序列化性能比java序列化高10倍左右]
    *       在实际工作中一般选择使用kryo序列化
    * 如何配置spark使用kryo序列化: *******
    *        1、在sparkconf中配置spark默认使用的序列化器: new SparkConf().set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
    *        2、注册哪些类使用kryo序列化【可选】: new SparkConf().registerKryoClasses(Array(classOf[待序列化的类的类名])
    *             使用registerKryoClasses与不使用registerKryoClasses的区别:
    *                 使用registerKryoClasses，此时再序列化类的时候不会将类的全类名序列化进去
    *                 没有使用registerKryoClasses，此时在序列化类的时候会将类的全类名序列化进去
    *
    */
  def main(args: Array[String]): Unit = {


    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test").set("spark.serializer","org.apache.spark.serializer.KryoSerializer").registerKryoClasses(Array(classOf[Student])) )

    val rdd = sc.parallelize(List( 10,3,2,6,5,10 ))

    val x = 100

    val person = new Person
    //val rdd2 = rdd.map( a => a * person.p)
    val rdd2 = person.m1(rdd)

    println(rdd2.collect().toList)

  }

  val a  =10

  val func = (x:Int) => x + a
}


case class Person() {

  val p = 10


  def m1(rdd:RDD[Int]):RDD[Int] = {

    rdd.map(x=> x * p)
  }
}