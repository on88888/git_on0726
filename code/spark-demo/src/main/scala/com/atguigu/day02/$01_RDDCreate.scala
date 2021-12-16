package com.atguigu.day02

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

class $01_RDDCreate {
  val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )
  /**
    * rdd的创建方式:
    *     1、根据本地创建
    *     2、读取文件创建
    *     3、其他rdd衍生
    */

  /**
    * 根据本地创建RDD
    *     makeRDD: 底层就是使用的parallelize
    *     parallelize
    */
  @Test
  def createRddByLocalCollection():Unit ={



    val list = List(1,4,3,2,6,10)
    val rdd = sc.makeRDD(list)
    println(rdd.collect().toList)

    val rdd2 = sc.parallelize(list)
    val result = rdd2.collect()
    println(result.toList)

  }


  /**
    * 根据读取文件创建RDD
    *   spark读取文件的方式:
    *     1、如果集群配置文件中有配置HADOOP_CONF_DIR配置,此时默认读取是HDFS文件 【公司一般有配置HADOOP_CONF_DIR】
    *           读取HDFS文件
    *               1、sc.textFile("/../...")  [默认情况]
    *               2、sc.textFile("hdfs:///../...")
    *               3、sc.textFile("hdfs://namenode ip:端口/../..")
    *           读取本地文件： sc.textFile("file:///../..")
    *     2、如果集群配置文件中没有配置HADOOP_CONF_DIR配置,此时默认读取是本地文件
    *           读取HDFS文件
    *               1、sc.textFile("hdfs://namenode ip:端口/../..")
    *           读取本地文件：
    *               1、sc.textFile("file:///../..")
    *               2、sc.textFile("/../...")  [默认情况]
    *
    */
  @Test
  def createRddByFile(): Unit ={

    val rdd = sc.textFile("datas/taxi.txt")
    val rdd2 = sc.textFile("hdfs://hadoop102:8020/input/taxi.txt")

    println(rdd2.collect().toList)
  }

  /**
    * 根据其他rdd衍生
    */
  @Test
  def createRddByRdd(): Unit ={
    val rdd = sc.textFile("datas/taxi.txt")

    val rdd2 = rdd.flatMap(_.split(" "))

    println(rdd2.collect().toList)
  }

}
