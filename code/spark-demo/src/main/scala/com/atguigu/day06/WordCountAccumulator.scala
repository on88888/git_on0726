package com.atguigu.day06

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
  * 自定义累加器:
  *     1、创建一个class继承AccumulatorV2[IN,OUT]
  *           IN: 累加的元素类型
  *           OUT: 最终结果类型
  *     2、重写抽象方法
  * 使用自定义累加器:
  *     1、创建一个自定义累加器对象: val acc = new WordCountAccumulator
  *     2、注册累加器对象: sc.register(acc[,"累加器名称"])
  */
class WordCountAccumulator  extends AccumulatorV2[(String,Int),mutable.Map[String,Int]]{
  //中间结果容器
  val tmpMap = mutable.Map[String,Int]()

  /**
    * 判断累加器是否为空
    * @return
    */
  override def isZero: Boolean = tmpMap.isEmpty

  /**
    * 复制累加器
    * @return
    */
  override def copy(): AccumulatorV2[(String, Int), mutable.Map[String, Int]] = new WordCountAccumulator

  /**
    * 重置累加器
    */
  override def reset(): Unit = tmpMap.clear()

  /**
    * 累加器元素【累加每个分区的数据】
    * @param v
    */
  override def add(v: (String, Int)): Unit = {
    //
/*    if(tmpMap.contains(v._1)){
      val count = tmpMap.get(v._1).get
      val total = count + v._2

      tmpMap.put(v._1,total)
    }else{

      tmpMap.put(v._1,v._2)
    }*/
    //第三次传递 scala->1 给 add方法
    // 	tmpMap= mutable.Map[String,Int]( hello->1, spark->1)

    println(s"add ${Thread.currentThread().getName} -- 传入数据: ${v}  --之前计算的中间结果: ${tmpMap}")
    val count = tmpMap.getOrElse(v._1,0) + v._2

    tmpMap.put(v._1,count)

  }

  /**
    * 合并所有task的结果 [在driver执行]
    * @param other
    */
  override def merge(other: AccumulatorV2[(String, Int), mutable.Map[String, Int]]): Unit = {
    println(s"merge  ${Thread.currentThread().getName} -- 传入的一个task汇总数据数据: ${other.value}  --之前Driver的汇总结果: ${tmpMap}")
      //获取task累加器结果
      val taskMap = other.value
      //taskMap = mutable.Map[String,Int](spark->1, kafka->2,flume->2,scala->1)
      //将task结果与driver之前的汇总合并
    //tmpMap = mutable.Map[String,Int]( hello->2,scala->1,spark->2,python->1 )
    val totalList = taskMap.toList ++ tmpMap.toList
    //List( spark->1, kafka->2,flume->2,scala->1 ,hello->2,scala->1,spark->2,python->1  )
    val groupedMap = totalList.groupBy(x=>x._1)
    //Map(
    //    spark-> List(spark->1, spark->2)
    //     scala -> List( scala->1,scala->1 )
    //  ....
    // )
    val result = groupedMap.map(x=>{
      //x = spark-> List(spark->1, spark->2)
      val sum = x._2.map(_._2).sum
      (x._1,sum)
    })
    //Map( hello->2,scala->2,spark->3,kafka->2,flume->2,python->1 )
    //将本次结果放入临时变量容器中，便于下一次与下一个task累加器汇总
    tmpMap.++=( result )
  }

  /**
    * 返回最终结果
    * @return
    */
  override def value: mutable.Map[String, Int] = tmpMap
}


//add Executor task launch worker for task 0 -- 传入数据: (hello,1)  --之前计算的中间结果: Map()
//add Executor task launch worker for task 0 -- 传入数据: (spark,1)  --之前计算的中间结果: Map(hello -> 1)
//add Executor task launch worker for task 0 -- 传入数据: (hello,1)  --之前计算的中间结果: Map(spark -> 1, hello -> 1)
//add Executor task launch worker for task 0 -- 传入数据: (python,1)  --之前计算的中间结果: Map(spark -> 1, hello -> 2)
//add Executor task launch worker for task 0 -- 传入数据: (scala,1)  --之前计算的中间结果: Map(spark -> 1, python -> 1, hello -> 2)
//add Executor task launch worker for task 0 -- 传入数据: (spark,1)  --之前计算的中间结果: Map(spark -> 1, scala -> 1, hello -> 2, python -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (spark,1)  --之前计算的中间结果: Map()
//add Executor task launch worker for task 1 -- 传入数据: (kafka,1)  --之前计算的中间结果: Map(spark -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (flume,1)  --之前计算的中间结果: Map(spark -> 1, kafka -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (flume,1)  --之前计算的中间结果: Map(spark -> 1, flume -> 1, kafka -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (kafka,1)  --之前计算的中间结果: Map(spark -> 1, flume -> 2, kafka -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (scala,1)  --之前计算的中间结果: Map(spark -> 1, flume -> 2, kafka -> 2)
//merge  dag-scheduler-event-loop -- 传入的一个task汇总数据数据: Map(spark -> 2, scala -> 1, python -> 1, hello -> 2)  --之前Driver的汇总结果: Map()
//merge  dag-scheduler-event-loop -- 传入的一个task汇总数据数据: Map(spark -> 1, scala -> 1, flume -> 2, kafka -> 2)  --之前Driver的汇总结果: Map(spark -> 2, scala -> 1, hello -> 2, python -> 1)
//Map(spark -> 3, scala -> 2, flume -> 2, kafka -> 2, hello -> 2, python -> 1)