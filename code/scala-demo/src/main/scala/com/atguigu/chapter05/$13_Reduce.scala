package com.atguigu.chapter05

object $13_Reduce {

  /**
    * 3、根据指定规则对数组所有元素进行聚合
    * 数据: Array[Int](10,20,30,40)
    * 规则: 求和
    * 结果: 100
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val arr = Array[Int](10,20,30,40)

    val func = (agg:Int, curr:Int) => agg+curr
    println(reduce(arr, func))
    //简化
    println(reduce(arr, (agg:Int, curr:Int) => agg+curr))
    println(reduce(arr, (agg, curr) => agg+curr))
    println(reduce(arr, _+_))
  }

  def reduce(arr:Array[Int],func: ( Int, Int ) => Int) = {

    var tmp = arr(0)

    for(index <- 1 until arr.length){

      tmp = func(tmp,arr(index))
    }

    tmp
  }
}
