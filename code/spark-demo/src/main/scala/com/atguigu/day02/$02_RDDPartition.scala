package com.atguigu.day02

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

class $02_RDDPartition {

  val sc:SparkContext = new SparkContext(new SparkConf().setMaster("local[4]").setAppName("test") /*.set("spark.default.parallelism","10")*/)

  /**
    * 通过集合创建的RDD的分区数
    *     1、parallelize方法numSlices参数有设置, 分区数 = numSlices的值
    *     2、parallelize方法numSlices参数没有设置, 分区数 = defaultParallelism
    *           defaultParallelism的值分为两种情况:
    *               1、有配置spark.default.parallelism参数,分区数 = spark.default.parallelism参数值
    *               2、没有配置spark.default.parallelism参数,
    *                     1、master=local, 分区数 = 1
    *                     2、master=local[N], 分区数 = N
    *                     3、maser=local[*] ,分区数 = cpu个数
    *                     4、master=spark://.../.. ,分区数 = math.max( 本次任务的所有executor cpu总个数 , 2 )
    *
    */

  @Test
  def createRDDByCollectionPartition(): Unit ={

    //val rdd = sc.parallelize(List(1,3,2,4,6,8,10),7)
    val rdd = sc.parallelize(List(1,3,2,4,6,8,10))

    //查看分区数
    println(rdd.partitions.size)
    println(rdd.getNumPartitions)

    val func = (index:Int, it:Iterator[Int]) =>{
      println(s"分区号=${index} 分区数据=${it.toList}")
      it
    }
    rdd.mapPartitionsWithIndex(func).collect()
  }

  /**
    * 根据文件创建的RDD的分区数, 分区数 >= minPartitions
    *     minPartitions默认 =  math.min(defaultParallelism, 2)
    *     分区数最终是多少个由文件的切片决定。
    */
  @Test
  def createRddByFilePartition(): Unit ={

    val rdd = sc.textFile("datas/wc.txt",4)

    println(rdd.getNumPartitions)
  }

  /**
    * 根据其他rdd衍生出新RDD的分区数 = 依赖的第一个rdd的分区数
    */
  @Test
  def createRddByRddPartition(): Unit ={
    val rdd1 = sc.textFile("datas/wc.txt",4)

    val rdd2 = rdd1.flatMap(_.split(" "))

    println(rdd2.getNumPartitions)
  }


}
