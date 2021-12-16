package com.atguigu.chapter05

object $15_MaxBy {

  /**
    * 5、根据指定规则获取数组最大元素
    * 数据: Array[String]("zhangsan 25 4000","wangwu 18 6000","zhaoliu 45 1200")
    * 规则: 获取年龄最大的元素
    * 结果: "zhaoliu 45 1200"
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    val arr= Array[String]("zhangsan 25 4000","wangwu 18 6000","zhaoliu 45 1200")

    val func = (x:String) => x.split(" ")(2).toInt
    println(maxBy(arr,func))
    println(maxBy(arr,(x:String) => x.split(" ")(2).toInt))
    println(maxBy(arr,(x) => x.split(" ")(2).toInt))
    println(maxBy(arr,_.split(" ")(2).toInt))
  }

  def maxBy(arr:Array[String],func: (String)=>Int) = {

    var tmp = func(arr(0))
    var result = arr(0)

    for(element<- arr){

      val current = func(element)

      if(current>=tmp){
        tmp = current
        result = element
      }
    }

    result
  }
}
//递归
