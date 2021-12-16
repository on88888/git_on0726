package com.atguigu.chapter07

import scala.io.Source

object $14_WordCountLow {

  //词频统计
  def main(args: Array[String]): Unit = {

    //1、读取数据
    val datas = Source.fromFile("datas/wc.txt").getLines().toList
    //List(
    // "hello java hello spark",
    // "spark hadoop hadoop flume",
    // "flume kafka spark scala",
    // "scala hadoop flume spark"
    // )

    //2、切割 map +压平 flatten
    val splitList = datas.flatMap( x=> x.split(" ") )

    //List(hello,java,hello,spark,spark,hadoop,hadoop,flume,...)

    //3、按照单词分组
    val groupedMap = splitList.groupBy( x => x)

    //Map(
    //    hello-> List( hello,hello ),
    //    java -> List(java),
    //    spark -> List(spark,spark,spark,spark)
    //    .......
    // )

    //4、统计次数
    val result = groupedMap.map(x=>{
      //x = hello-> List( hello,hello ),

      (x._1, x._2.size)
    })

    //5、结果展示
    result.foreach(println)
    //Map(hello->2,java->1,spark->4,....)


    Source.fromFile("datas/wc.txt")
      .getLines()
      .toList
      .flatMap(_.split(" "))
      .groupBy(x=>x)
      .map(x=>(x._1,x._2.length))
      .foreach(println)
  }
}
