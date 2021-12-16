package com.atguigu.day08

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction, UserDefinedFunction}
import org.apache.spark.sql.types._

/**
  * 弱类型自定义UDAF函数
  */
class MyAvgUDAF1 extends UserDefinedAggregateFunction{

  /**
    * UDAF函数是输入参数类型
    * @return
    */
  override def inputSchema: StructType = {
      //StructType(Array(StructField("xx",IntegerType)))
    new StructType()
      .add("input",IntegerType)
  }

  /**
    * 指定计算过程中需要的中间变量的类型
    * @return
    */
  override def bufferSchema: StructType = {
    new StructType()
      .add("sum",IntegerType)
      .add("count",IntegerType)
  }

  /**
    * 指定最终计算的结果类型
    * @return
    */
  override def dataType: DataType = DoubleType

  /**
    * 一致性的指定
    * @return
    */
  override def deterministic: Boolean = true

  /**
    * 指定中间变量的初始值
    * @param buffer
    */
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    //设置sum的初始值
    buffer(0)  = 0
    //设置count的初始值
    buffer(1) = 0

  }

  /**
    * 预聚合操作[combiner阶段聚合]【针对每个组】
    * @param buffer
    * @param input： 组中一个值
    */
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      println(s"update ${Thread.currentThread().getName} 上一次结果数据: ${buffer}  本次传入的元素:${input}")
      //更新sum值  sum = sum + 元素
      buffer(0) = buffer.getAs[Int](0) + input.getAs[Int](0)
      //更新count的值
      buffer(1) = buffer.getAs[Int](1) + 1
  }

  /**
    * reducer阶段聚合【针对每个组】
    * @param buffer1 上一次汇总结果
    * @param buffer2  某一个分区的聚合结果
    */
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    println(s"merge ${Thread.currentThread().getName} 上一次汇总结果: ${buffer1}  分区聚合结果:${buffer2}")
    //汇总sum
    buffer1(0) = buffer1.getAs[Int](0) + buffer2.getAs[Int](0)
    //汇总count
    buffer1(1) = buffer1.getAs[Int](1) + buffer2.getAs[Int](1)
  }


  /**
    * 计算返回最终结果
    * @param buffer
    * @return
    */
  override def evaluate(buffer: Row): Any = {
    buffer.getAs[Int](0).toDouble / buffer.getAs[Int](1)
  }
}
