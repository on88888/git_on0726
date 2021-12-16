package com.atguigu.chapter07

object $15_WordCountHight {

  def main(args: Array[String]): Unit = {

    val tupleList = List( ("Hello Scala Spark World", 4), ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))

    //1、切割[需要初始化单词的次数] + 压平
    val splitList = tupleList.flatMap(x=>{
      //x = ("Hello Scala Spark World", 4)

      val arr = x._1.split(" ")
      //Array(Hello,Scala,Spark,World)

      arr.map(y=> (y,x._2))
    })
    //List( Hello->4,Scala->4,Spark->4,World->4,Hello->3,Scala->3,Spark->3,Hello->2,Scala->2,Hello->1)

    //2、按照单词分组
    val groupMap = splitList.groupBy(x=> x._1 )

    //Map(
    //    Hello-> List( Hello->4 ,Hello->3, Hello->2,Hello->1 )
    //    Scala-> List( Scala->4 ,Scala->3, Scala->2)
    //    Spark-> List( Spark->4 ,Spark->3)
    //    World-> List( World->4)
    // )

    //3、统计每个单词的总次数
    val result = groupMap.map(x=>{
      //x = Hello-> List( Hello->4 ,Hello->3, Hello->2,Hello->1 )
      //第一种:
      //val aggregate = x._2.reduce((agg,curr)=>{
      //  //agg = Hello->4  curr=Hello->3
      //  (agg._1 ,agg._2 + curr._2)
      //})

      //aggregate

      //第二种:
      val count = x._2.map(y=> y._2).sum
      (x._1,count)
    })
    //4、结果展示
    result.foreach(println)
  }
}
