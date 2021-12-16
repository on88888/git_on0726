package com.atguigu.day06

import org.apache.spark.RangePartitioner

object $01_Partitioner {

  /**
    * 分区器: 用于shuffle阶段
    *     spark自带的分区器:
    *           HashPartitioner:
    *               分区规则:   key.hashCode % 分区数 < 0 ?  key.hashCode % 分区数 + 分区数 :  key.hashCode % 分区数
    *           RangePartitioner:
    *               分区规则: 会采用采样的方式从数据集中抽取 分区数-1 个key, 通过这几个采样的key确定每个分区的边界，后续将key与分区边界对比如果在边界范围内则放入对应分区中。
    *
    */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    val rdd = sc.parallelize(List(1,3,6,2,8,9,10,4,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1))

    val rdd2 = rdd.map(x=>(x,null))

    val rdd3 = rdd2.partitionBy(new RangePartitioner(3,rdd2))

    rdd3.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()

    Thread.sleep(1000000)
  }
}
