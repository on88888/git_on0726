package com.atguigu.day08

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator

/**
  * 强类型自定义udaf函数
  *     1、定义class继承Aggregator[IN,buff,out]
  *         IN: 函数参数类型
  *         BUF: 中间变量类型
  *         OUT: 最终结果类型
  */

case class AvgBuffer(sum:Int,count:Int)

class MyAvgUDAF2 extends Aggregator[Int, AvgBuffer, Double]{
  /**
    * 初始化中间变量
    * @return
    */
  override def zero: AvgBuffer = AvgBuffer(0,0)

  /**
    * combiner阶段在分区内针对每个组进行预聚合[相当于update]
    * @param b
    * @param a
    * @return
    */
  override def reduce(buff: AvgBuffer, adid: Int): AvgBuffer = {
    val sum = buff.sum + adid
    val count = buff.count+1
    AvgBuffer(sum,count)
  }

  /**
    * 在reducer阶段的汇总逻辑
    * @param b1
    * @param b2
    * @return
    */
  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    val sum = b1.sum + b2.sum
    val count = b1.count + b2.count
    AvgBuffer(sum,count)
  }

  /**
    * 计算最终结果返回
    * @param reduction
    * @return
    */
  override def finish(reduction: AvgBuffer): Double = {
    reduction.sum.toDouble/reduction.count
  }

  /**
    * 中间变量类型的编码方式
    * @return
    */
  override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product[AvgBuffer]

  /**
    * 最终结果类型的编码方式
    * @return
    */
  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}
