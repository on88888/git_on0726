package com.atguigu.chapter05

import java.util

object $14_GroupBy {

  /**
    * 4、根据指定规则对数组的数据进行分组
    * 数据: Array[String]("zhangsan man shenzhen","wangwu woman beijing","zhaoliu woman shenzhen")
    * 规则: 按照地址分组
    * 结果: Map( shenzhen->List("zhangsan man shenzhen","zhaoliu woman shenzhen" ) , beijing->List("wangwu woman beijing") )
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val arr = Array[String]("zhangsan man shenzhen","wangwu woman beijing","zhaoliu woman shenzhen")

    val func = (x:String) => x.split(" ")(1)
    println(groupBy(arr,func))
    println(groupBy(arr,(x:String) => x.split(" ")(1)))
    println(groupBy(arr,(x) => x.split(" ")(1)))
    println(groupBy(arr,_.split(" ")(1)))
  }

  def groupBy(arr:Array[String],func: String => String )={

    val map = new util.HashMap[String,util.List[String]]()

    for(element<- arr){
      //获取分组的key
      val key = func(element)

      if(map.containsKey(key)){

        val list = map.get(key)

        list.add(element)
      }else{

        val list = new util.ArrayList[String]()
        list.add(element)

        map.put(key,list)
      }

    }

    map
  }
}
