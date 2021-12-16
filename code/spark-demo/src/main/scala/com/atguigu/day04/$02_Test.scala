package com.atguigu.day04

import org.apache.spark.rdd.RDD

object $02_Test {

  /**
    * 统计每个省份点击量最多的三个广告
    */
  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    //1、读取数据
    val rdd1 = sc.textFile("datas/agent.log")

    //2、列裁剪[ 省份 广告id ]
    val rdd2 = rdd1.map( line=>{
      val arr = line.split(" ")
      ( (arr(1), arr.last),1  ) //(省份,广告)
    } )
    //3、按照省份+广告分组，统计每个省份每个广告点击次数
    //val rdd3: RDD[((String, String), Iterable[(String, String)])] = rdd2.groupBy(x=>x)
    val rdd3 = rdd2.reduceByKey(_+_)
//    println(rdd3.sortByKey().collect().toList)
    //RDD(
    //    (湖北省,A)->10,
    //    (湖北省,B)->30,
    //    (湖北省,C)->100,
    //    (湖南省,D)->1,
    //    (湖南省,F)->33,
    //    .....
    // )
    //4、按照省份分组
    val rdd4 = rdd3.groupBy{
      case ( (province,ad),num ) => province
    }
    //RDD(
    //    湖北省 -> List( (湖北省,A)->10, (湖北省,B)->30,  (湖北省,C)->100, ...)
    //     ....
    // )
    //5、对每个省份所有广告数据排序取前三
    val rdd5 = rdd4.map(x=>{
      //x = 湖北省 -> List( (湖北省,A)->10, (湖北省,B)->30,  (湖北省,C)->100, ...)
      val top3 = x._2.toList.sortBy(_._2).reverse.take(3).map{
        case ( (province,ad),num ) => (ad,num)
      }
      (x._1, top3)
    })

    //6、结果展示
    rdd5.collect().foreach(println)
  }
}
