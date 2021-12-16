package com.atguigu.chapter07

import scala.collection.mutable.ArrayBuffer

object $02_MutableArray {

  /**
    * 可变数组的创建
    *     1、通过new方式: new ArrayBuffer[元素类型]()
    *     2、通过apply方法: ArrayBuffer[元素类型](初始元素,...)
    *
    */
  def main(args: Array[String]): Unit = {

    //通过new方式: new ArrayBuffer[元素类型]()
    val arr1 = new ArrayBuffer[Int]()

    //通过apply方法: ArrayBuffer[元素类型](初始元素,...)
    val arr = ArrayBuffer[Int](10,3,2,6,7)

    println(arr1)
    println(arr)

    //添加元素
    //添加单个元素
    val arr2 = arr.+:(30)

    val arr3 = arr.:+(40)

    println(arr2)
    println(arr3)
    println(arr)

    arr.+=(50)
    println(arr)

    arr.+=:(60)

    println(arr)

    //添加一个集合所有元素
    val arr7 = arr.++(Array(20,30,40))

    println(arr7)

    val arr8 = arr.++:(Array(20,30,40))
    println(arr8)

    arr.++=(Array(20,30,40))
    println(arr)

    arr.++=:(Array(60,70,80))
    println(arr)

    println("-----------------------")
    println(arr)
    arr.insert(2,100,200,300)
    println(arr)

    //删除元素
    val arr9 = arr.-(60)
    println(arr9)
    println(arr)

    arr.-=(60)
    println(arr)

    val arr10 = arr.--(Array(10,20,30,4,5,6,7))
    println(arr10)

    arr.--=(Array(60,70,80))
    println(arr)

    arr.remove(2,3)
    println(arr)

    //更新角标数据： 数组名(角标) = 值
    arr(0)=1000
    println(arr)

    //获取角标数据: 数组名(角标)
    println(arr(0))

    //可变数组转不可变数组
    val arr11 = arr.toArray
    println(arr11)

    //多维数组
    val arr12 = Array.ofDim[Int](2,3)
    println(arr12.length)
    println(arr12(0).length)
  }
}
