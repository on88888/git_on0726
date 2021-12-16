package com.atguigu.chapter07

object $12_CollectionLowFunction {

  def main(args: Array[String]): Unit = {

    val list = List(1,3,7,9,10,2,5)

    //获取最大值
    println(list.max)
    //获取最小值
    println(list.min)
    //根据字段获取最大值: maxBy(func: 集合元素类型=> B )
    // maxBy后续是根据函数的返回值获取集合中最大的元素
    // maxBy里面的函数是针对集合每个元素操作
    val list2 = List( ("zhangsna",20,3),("lisi",30,4),("wangwu",25,1),("hanmeimei",50,10) ,("hanmeimei",18,10))
    //println(list2.max)
    val func = ( x:(String,Int,Int) ) => x._2
    println(list2.maxBy(func))
    //直接传递函数值
    println(list2.maxBy(( x:(String,Int,Int) ) => x._2))
    //函数的参数类型可以省略
    println(list2.maxBy(( x ) => x._2))
    //函数只有一个参数,参数列表的()可以省略
    println(list2.maxBy( x  => x._2))
    //函数的参数在函数体中只使用了一次可以用_简化
    println(list2.maxBy( _._2))

    println(list.maxBy(x => x))

    //根据指定字段获取最小值: minBy(func: 集合元素类型=> B )
    //后续minBy是根据函数的返回值获取集合最大元素
    //minBy里面的函数是针对集合每个元素操作
    println(list2.minBy(x => x._2))

    //求和
    println(list.sum)

    //排序
    //sorted: 是直接按照集合元素本身排序【默认升序】
    //升序
    println(list.sorted)
    //降序
    println(list.sorted.reverse)

    println(list2.sorted)

    //sortBy(func: 集合元素类型=> B)<重点>: 按照指定字段排序 【默认升序】 *********
    //sortBy后续是按照函数的返回值进行排序
    //sortBy里面的函数是针对集合每个元素操作
    //升序
    println(list2.sortBy(x => x._2))
    //降序
    println(list2.sortBy(x => x._2).reverse)
    //降序
    val ordering = new Ordering[Int]{
      override def compare(x: Int, y: Int): Int = {
        if(x<y) 1
        else if(x==y) 0
        else -1
      }
    }
    println(list2.sortBy(x => x._2)(ordering))

    //sortWith(func: (集合元素类型,集合元素类型)=>Boolean): 根据规则排序
    //降序
    println(list.sortWith((x, y) => x > y))
    //升序
    println(list.sortWith((x, y) => x < y))

  }
}
