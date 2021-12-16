package com.atguigu.chapter05

object $11_Map {

  /**
    * 1、根据指定规划对数组中每个元素进行转换
    * 数据: Array[String]("spark","hadoop","flume","kafka")
    * 规则: 获取每个元素的长度【动态可变】
    * 结果: Array[Int](5,6,5,5)
    */
  def main(args: Array[String]): Unit = {

    val arr =  Array[String]("spark","hadoop","flume","kafka")

    val func = (x:String) => x.length
    println(map(arr,func).toList)
    //直接传递函数的值
    println(map(arr, (x: String) => x.charAt(0)).toList)
    //省略函数的参数类型
    println(map(arr, (x) => x.charAt(0)).toList)
    //使用_代替
    println(map(arr, _.charAt(0)).toList)
  }

  def map( datas:Array[String] ,func: String=> Any) = {

    for( element<- datas) yield {
      //element.length
      //element.charAt(0)
      func(element)
    }
  }
}
