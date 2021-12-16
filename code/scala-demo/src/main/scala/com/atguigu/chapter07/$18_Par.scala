package com.atguigu.chapter07

object $18_Par {

  /**
    * 并行集合: 多线程操作同一个集合
    * scala集合默认是单线程操作,如果想要多线程操作,只需要通过 集合名.par 转换即可
    */
  def main(args: Array[String]): Unit = {

    val list = List(1,4,2,3,6)

    list.foreach( x=> {
      println(s"${Thread.currentThread().getName}")
    } )

    println("-"*100)
    list.par.foreach( x=> {
      println(s"${Thread.currentThread().getName}")
    } )
  }
}
