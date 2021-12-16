package com.atguigu.chapter07

object $13_CollectionHightFunction {

  def main(args: Array[String]): Unit = {


    //map(func: 集合元素类型=> B ): 一对一的映射 ********
    //map里面的函数是针对集合每个元素操作,集合元素有多少个,函数就调用多少次
    //map生成的新集合的元素个数 = 原集合元素个数
    //map的使用场景: 一般用于数据类型的转换/值的转换
    val list = List("hadoop","flume","spark","scala","kafka")
    val func = (x:String) => x.length
    val list2 = list.map(func)
    println(list2)

    //flatten : 压平
    //flatten针对的是集合嵌套集合的数据类型
    //flatten是将第二层集合压平
    //flatten生成的新集合的元素个数>= 原集合元素个数
    //flatten的应用场景: 一对多
    val list3: List[List[Int]] = List(List(1),List(4),List(6))
    val list4: List[Int] = list3.flatten
    println(list4)


    val list5 = List[List[List[Int]]](
      List(
        List(1,2),
        List(3,4)
      ),
      List(
        List(5,6),
        List(7,8)
      )
    )

    val list6 = list5.flatten
    println(list6)

    val list7: List[String] = List("hello","java")
    println(list7.flatten)

    // flatMap(func: 集合元素类型=> 集合) = map + flatten   ********
    // flatMap里面的函数是针对集合每个元素操作,元素有多少个,函数就调用多少次
    // flatMap生成的新集合元素个数>= 原集合元素个数
    // flatMap的应用场景: 一对多
    // flatten与flatMap的区别:
    //      flatten: 只是单纯的压平
    //      flatMap: 是先转换数据,然后再压平
    val list8 = List("hello java spark","hello spark python","python spark scala")
    val list9 = list8.map(x=>{
      x.split(" ")
    })
    println(list9)
    //List("hello","java","spark",....)
    val list10 = list9.flatten
    println(list10)

    val list11 = list8.flatMap(x=> x.split(" "))
    println(list11)

    //foreach(func: 集合元素类型=> B):Unit  : 遍历  ********
    //foreach里面的函数是针对集合每个元素操作,元素有多少个,函数就调用多少次
    //foreach不会生成新的集合,foreach没有返回值
    //foreach与map的区别:
    //    foreach相当于没有yield关键字的for循环,foreach没有返回值
    //    map相当于有yield关键字的for循环,map会生成一个新的集合
    list.foreach(x => println(x) )
    list.foreach(println(_))
    list.foreach(println)

    //filter(func: 集合元素类型=> Boolean ): 根据指定条件过滤 ********
    // filter里面的函数也是针对集合每个元素操作,有多少元素,函数就调用多少次
    // filter保留是函数返回值为true
    val list12 = List(1,4,2,7,9,10)

    val list13 = list12.filter( x=> x%2==0 )
    println(list13)

    //groupBy(func: 集合元素类型 => K ): 根据指定字段分组 ********
    //groupBy里面的函数也是针对集合每个元素操作
    //groupBy后续是按照函数的返回值对集合元素进行分组
    //groupBy的结果是Map[K,V],map中K就是函数的返回值,V就是K在原集合中对应的所有元素
    val list14 = List(("zhangsan","man","shenzhen"),("lisi","woman","beijing"),("wangwu","man","beijing"))

    val map = list14.groupBy(x=> x._2 )

    println(map)

    //reduce(func: (集合元素类型,集合元素类型)=>集合元素类型 ): 从左向右聚合
    //reduce函数中第一个参数代表上一次聚合结果[第一次聚合的时候,函数第一个参数的初始值 = 集合第一个元素],第二个参数代表当前要聚合的元素
    val list15 = List(1,3,5,2,8,10)
    val r = list15.reduce( (agg,curr)=> {
      println(s"reduce agg=${agg} curr=${curr}")
      agg+curr
    } )
    println(r)
    //reduceRight(func: (集合元素类型,集合元素类型)=>集合元素类型 ): 从右向左聚合
    //reduceRight函数中第二个参数代表上一次聚合结果[第一次聚合的时候,函数第二个参数的初始值 = 集合最后一个元素],第一个参数代表当前要聚合的元素
    list15.reduceRight( (curr,agg)=>{
      println(s"reduceRight agg=${agg} curr=${curr}")
      agg+curr
    } )

    //fold(默认值)(func: (集合元素类型,集合元素类型)=>集合元素类型 ): 从左向右聚合
    //fold函数中第一个参数代表上一次聚合结果[第一次聚合的时候,函数第一个参数的初始值 = 默认值],第二个参数代表当前要聚合的元素
    list15.fold(1000)( (agg,curr)=> {
      println(s"fold agg=${agg} curr=${curr}")
      agg+curr
    }  )

    //foldRight(默认值)(func: (集合元素类型,集合元素类型)=>集合元素类型 ): 从右向左聚合
    //foldRight函数中第二个参数代表上一次聚合结果[第一次聚合的时候,函数第二个参数的初始值 = 默认值],第一个参数代表当前要聚合的元素
    list15.foldRight(-1)( (curr,agg)=>{
      println(s"foldRight agg=${agg} curr=${curr}")
      agg+curr
    } )
  }
}
