package com.atguigu.chapter07

object $08_ImmutableMap {

  /**
    * 不可变Map创建: immutable.Map[K的类型,V的类型]( (K,V), K->V,... )
    *
    * Option: 为了提醒外部当前结果有可能为空,需要自己处理
    *     Some: 代表不为空,结果封装在Some中,后续可以调用get方法获取结果
    *     None: 代表为空
    *map获取元素:
    *     getOrElse(key,默认值): 如果map中key存在则返回key对应的value值,如果key不存在则返回默认值
    */
  def main(args: Array[String]): Unit = {

    val map = Map[String,Int]("aa"->10, ("bb",20),"ac"->30,"dc"->40,"cc"->60)

    println(map)

    //添加元素
    val map2 = map.+( "cd"->100 )
    println(map2)

    val map3 = map.++( List( "ee"->70 ,"ff"->80) )
    println(map3)

    val map4 = map.++:(List( "gg"->200,"oo"->300))
    println(map4)

    //删除元素
    val map5 = map.-("ac")
    println(map5)

    val map6 = map.--(List("cc","dc"))
    println(map6)

    //获取元素
    //根据key获取value值
    //println(map("ac1"))
    //val option = map.get("ac1")
    //if(!option.isEmpty){
    //  println(option.get)
    //}
    println(map.getOrElse("ac1", -1))

    //获取所有的key
    for( key<- map.keySet) println(key)

    //获取所有的value值
    for(value<- map.values) println(value)

    //修改元素
    val map11 = map.+( "aa"->100 )
    println(map11)

    val map12 = map.updated("aa",1000)
    println(map12)

  }
}
