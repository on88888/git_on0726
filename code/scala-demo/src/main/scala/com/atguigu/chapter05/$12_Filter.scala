package com.atguigu.chapter05

object $12_Filter {

  /**
    * 2、根据指定规则对数组的数据进行过滤
    * 数据: Array[Int](1,4,2,6,8,10,9,7)
    * 规则: 只保留偶数数据【动态可变】
    * 结果: Array[Int](4,2,6,8,10)
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val arr = Array[Int](1,4,2,6,8,10,9,7)

    val func = (x:Int) => x%2==0
    val func2 = (x:Int) => x%2!=0
    println(filter(arr,func).toList)
    println(filter(arr,func2).toList)
    //直接传递函数的值
    println(filter(arr,(x:Int) => x%2!=0).toList)
    //省略函数参数类型
    println(filter(arr,(x) => x%2!=0).toList)
    //使用_简化
    println(filter(arr,_%2!=0).toList)

  }

  def filter(arr:Array[Int],func: Int => Boolean) = {

    for(element<- arr if(func(element))) yield {
        element
    }
  }
}
