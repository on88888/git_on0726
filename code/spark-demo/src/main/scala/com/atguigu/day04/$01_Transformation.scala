package com.atguigu.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner}
import org.junit.Test

class $01_Transformation/* extends Serializable */{

  import org.apache.spark.{SparkConf, SparkContext}
  @transient
  val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

  /**
    * 交集: 两个RDD共同的元素
   * 会产生shuffle,而且是两次shuffle操作
    */
  @Test
  def intersection(): Unit ={

    val rdd1 = sc.parallelize(List(1,2,3,4,5))
    val rdd2 = sc.parallelize(List(4,5,6,7,8))

    val rdd3 = rdd1.intersection(rdd2)

    println(rdd3.getNumPartitions)
    println(rdd3.collect().toList)

    Thread.sleep(1000000)

  }

  /**
    * 差集
    * 会产生shuffle,而且是两次shuffle操作
    */
  @Test
  def subtract(): Unit ={
    val rdd1 = sc.parallelize(List(1,2,3,4,5))
    val rdd2 = sc.parallelize(List(4,5,6,7,8))

    val rdd3 = rdd1.subtract(rdd2)

    println(rdd3.collect().toList)

    Thread.sleep(1000000)
  }

  /**
    * union: 并集
    *     union生成的新的RDD的分区数 = 父RDD分区数之和
   *     不会产生shuffle
    */
  @Test
  def union(): Unit ={
    val rdd1 = sc.parallelize(List(1,2,3,4,5))
    val rdd2 = sc.parallelize(List(4,5,6,7,8))

    val rdd3 = rdd1.union(rdd2)

    println(rdd3.getNumPartitions)
    println(rdd3.collect().toList)
//    Thread.sleep(1000000)

  }

  /**
    * 两个RDD要想拉链必须元素个数与分区数都相同
    */
  @Test
  def zip(): Unit ={

    val rdd = sc.parallelize(List("lisi","wangwu","zhoaliu","aa"),2)
    val rdd2 = sc.parallelize(List(20,30),1)

    val rdd3 = rdd.zip(rdd2)

    println(rdd3.collect().toList)
  }

  /**
    * partitionBy: 根据指定的分区器重分区
    */
  @Test
  def partitionBy(): Unit ={

    val rdd = sc.parallelize(List(1,3,2,5,4))

    val rdd2 = rdd.map(x=> (x,x) )
    rdd2.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()

    val rdd3 = rdd2.partitionBy(new HashPartitioner(6))

    println(rdd3.getNumPartitions)

    rdd3.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()
  }

  /**
    * 自定义分区器:
    *     1、定义一个class继承Partitioner抽象类
    *     2、重写抽象方法
    *
    */


  @Test
  def partition(): Unit ={
    val rdd = sc.parallelize(List(1,3,2,5,4))

    val rdd2 = rdd.groupBy(x=>x ,p= new MyPartitioner(3))

    rdd2.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()
  }

  /**
    * groupByKey: 根据key分组
    *     groupBykey生成的新RDD的元素是KV键值对
    *         K: 是分组的K
    *         V： 是K在原RDD中对应的所有的value值的集合
    */
  @Test
  def groupByKey(): Unit ={

    val rdd = sc.parallelize(List( ("aa",10) ,("cc",20),("aa",100),("dd",40),("cc",300)  ))

    val rdd2 = rdd.groupByKey()

    println(rdd2.collect().toList)
  }

  /**
    * reduceByKey(func: (value值类型, value值类型)=>value值类型 ): 按照key分组之后,对每个组所有的value值聚合
    *
    *     groupByKey与reduceByKey的区别:
    *           groupByKey: 只是单纯分组, shuffle过程中没有预聚合功能, shuffle效率比较低
    *           reduceByKey: 分组+聚合, shuffle过程中有预聚合功能, shuffle效率更高
    *     工作中尽量使用高性能的shuffle算子
    */
  @Test
  def reduceByKey(): Unit ={

    val rdd = sc.textFile("datas/wc.txt")

    val rdd2 = rdd.flatMap(_.split(" "))

    val rdd3 = rdd2.map(x=>(x,1))

    rdd3.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()

    val rdd4 = rdd3.reduceByKey( (agg,curr)=>{
      println(s"agg=${agg} curr=${curr}")
      agg+curr
    } )
    //val rdd3 = rdd2.groupBy(x=>x)

    //val rdd4 = rdd3.map(x=>(x._1,x._2.size))

    println(rdd4.collect().toList)
  }


  /**
    * combineByKey(createCombiner: RDDvalue值类型 => C,mergeValue: (C, RDDvalue值类型) => C,mergeCombiners: (C, C) => C)
    *     createCombiner: 在combiner阶段对每个组第一个value值进行转换
    *     mergeValue: combiner聚合逻辑
    *     mergeCombiners： reducer聚合逻辑
    */
  @Test
  def combineByKey(): Unit ={

    val rdd = sc.parallelize(List( ("语文",60),("英语",100),"英语"->80,"数学"->100,"英语"->70,"语文"->70,"数学"->80,"语文"->100,"数学"->80,"英语"->60,"英语"->50,"数学"->100,"数学"->100,"语文"->100 ,"英语"->80),2)

    //需求: 统计每门学科的平均分
    val rdd2 = rdd.groupByKey()
    //RDD(
    //    语文-> List( 60,70,100,100 ,80),
    //    数学->List( 100,80,100,100,80)
    //    英语->List( 100,70,60,50,80)
    // )
    val rdd3 = rdd2.map(x=>(x._1, x._2.sum/x._2.size))

    println(rdd3.collect().toList)

    //rdd.reduceByKey( (agg,curr)=> agg+curr )
    val rdd4 = rdd.map(x=>( x._1, (x._2,1 )  ))
    //Rdd(
    //    "语文" -> (60,1),
    //    "英语" -> (100,1),
    //    "英语" -> (80,1),
    //    "数学" -> (100,1),
    //    .....
    // )
    val rdd5 = rdd4.reduceByKey( (agg,curr)=>  ( agg._1+curr._1, agg._2+curr._2 ))
    //按照key分组..聚合
    //语文-> List( (60,1),(70,1),(100,1),(100,1) ,(80,1))
    //List((数学,(460,5)), (英语,(440,6)), (语文,(330,4)))
    //println(rdd5.collect().toList)
    val rdd6 = rdd5.map{
      case (name,(score,num)) => (name,score/num)
    }

    println(rdd6.collect().toList)

    //---------------------------------------------------------------------------------------

    rdd.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()

    val rdd7 = rdd.combineByKey(x=> (x,1) , (agg:(Int,Int) ,curr: Int) => {
      println(s"combiner阶段: agg=${agg} curr=${curr}")
      (agg._1+curr, agg._2+1)
    } , (agg: (Int,Int) ,curr:(Int,Int))=> {
      println(s"reducer阶段: agg=${agg} curr=${curr}")
      (agg._1+curr._1, agg._2+curr._2)
    })
    val rdd8 = rdd7.map{
      case (name,(score,num)) => (name,score/num)
    }
    println(rdd8.collect().toList)
  }

  /**
    * foldByKey(默认值)(func: (value值类型,value值类型)=>value值类型) ： 按照key分组,对每个组所有value值聚合
    *     foldByKey在combiner阶段针对每个组第一次计算的时候, 函数第一个参数的初始值 = 默认值
    */
  @Test
  def foldByKey(): Unit ={

    val rdd = sc.parallelize(List( ("spark",3),("hadoop",4),("spark",5),("hadoop",4),"scala"->3,"hadoop"->10 ),2)

    rdd.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()

    val rdd2 = rdd.foldByKey(0)( (agg,curr)=> {
      println(s"agg=${agg} curr=${curr}")
      agg+curr
    } )

    println(rdd2.collect().toList)
  }

  /**
    * aggregateByKey(默认值)(seqOp,combOp)
    *      seqOp: combiner聚合函数
    *         combiner阶段针对每个组第一个计算的时候, seqOp函数的第一个参数的初始值 = 默认值
    *      combOp: reducer聚合函数
    *
    */
  @Test
  def aggregateByKey(): Unit ={
    val rdd = sc.parallelize(List( ("语文",60),("英语",100),"英语"->80,"数学"->100,"英语"->70,"语文"->70,"数学"->80,"语文"->100,"数学"->80,"英语"->60,"英语"->50,"数学"->100,"数学"->100,"语文"->100 ,"英语"->80),2)

    val rdd2 = rdd.aggregateByKey( (0,0) ) ( (agg:(Int,Int) ,curr: Int)=> {
      println(s"combiner计算: agg=${agg} curr=${curr}")
      (agg._1+curr, agg._2+1)
    } , (agg,curr)=> {
      println(s"reducer计算: agg=${agg} curr=${curr}")
      (agg._1+curr._1,agg._2+curr._2 )
    })

    println(rdd2.collect().toList)
  }

  /**
    * reduceBykey:
    * combineByKeyWithClassTag[V]((v: V) => v, func, func, partitioner)
    *
    * combineByKey:
    * combineByKeyWithClassTag(createCombiner, mergeValue, mergeCombiners)(null)
    *
    * foldByKey:
    * combineByKeyWithClassTag[V]((v: V) => cleanedFunc(默认值, v),cleanedFunc, cleanedFunc, partitioner)
    *
    * aggregateBykey:
    * combineByKeyWithClassTag[U]((v: V) => cleanedSeqOp(默认值, v),cleanedSeqOp, combOp, partitioner)
    *
    * reduceByKey、foldByKey、combineByKey、aggregateByKey区别:
    *     reduceByKey: 在combiner阶段对每个组所有value值第一次计算的时候,函数的第一个参数的初始值  = 该组第一个value值,  combiner与reducer计算逻辑完全一样
    *     foldByKey: 在combiner阶段对每个组所有value值第一次计算的时候,函数的第一个参数的初始值  = 默认值,  combiner与reducer计算逻辑完全一样
    *     combinerByKey: 在combiner阶段对每个组所有value值第一次计算的时候,函数的第一个参数的初始值 = 第一个函数的转换结果
    *     aggregateByKey: 在combiner阶段对每个组所有value值第一次计算的时候,函数的第一个参数的初始值 = 默认值
    */

  /**
    * sortByKey: 根据key排序
    */
  @Test
  def sortByKey(): Unit ={

    val rdd = sc.parallelize(List(1,4,3,2,6,7))

    val rdd2 = rdd.map( x=> (x,null) )

    val rdd3 = rdd2.sortByKey(false)

    val rdd4 = rdd3.map(x=>x._1)

    println(rdd4.collect().toList)

    val rdd5 = rdd.sortBy(x=>x)
    println(rdd5.collect().toList)
  }

  /**
    * map(func: 元素=>新元素)
    * mapValues(func: 元素的value值=>元素的新value值)： 一对一的映射[将原来的value值做转换,返回新的vlaue值]
    *       mapValues里面的函数是针对元素的value值操作, 元素有多少个,函数就调用多少次
    */
  @Test
  def mapValues(): Unit ={
    val rdd = sc.parallelize(List( ("语文",60),("英语",100),"英语"->80,"数学"->100,"英语"->70,"语文"->70,"数学"->80,"语文"->100,"数学"->80,"英语"->60,"英语"->50,"数学"->100,"数学"->100,"语文"->100 ,"英语"->80),2)

    val rdd2 = rdd.map(x=> (x._1,x._2/10))

    val rdd3 = rdd.mapValues(x=> x/10)

    println(rdd3.collect().toList)
  }

  /**
    * join: 两个rdd只有key相同的才能连接,join生成的RDD的元素类型是 (key,(左rdd value值,右rdd value值)) 【类似sql inner join】
    * leftOuterJoin: 两个rdd key相同的能连接+ 左rdd不能连接的数据,join生成的RDD的元素类型是 (key,(左rdd value值,Option(右rdd value值)))
    * rightOuterJoin: 两个rdd key相同的能连接+ 右rdd不能连接的数据,join生成的RDD的元素类型是 (key,(Option(左rdd value值),右rdd value值))
    * fullOuterJoin: 两个rdd key相同的能连接+ 右rdd不能连接的数据+左rdd不能连接的数据,join生成的RDD的元素类型是 (key,(Option(左rdd value值),Option(右rdd value值)))
    */
  @Test
  def join(): Unit ={

    val rdd1 = sc.parallelize(List("aa"->1,"cc"->2,"dd"->3,"ee"->4,"aa"->5))
    val rdd2 = sc.parallelize(List("aa"->10,"cc"->20,"tt"->30,"pp"->40))
    // inner join
    val rdd3: RDD[(String, (Int, Int))] = rdd1.join(rdd2)
    println(rdd3.collect().toList)

    val rdd4 = rdd1.leftOuterJoin(rdd2)

    println(rdd4.collect().toList)

    val rdd5 = rdd1.rightOuterJoin(rdd2)

    println(rdd5.collect().toList)

    val rdd6 = rdd1.fullOuterJoin(rdd2)
    println(rdd6.collect().toList)

    //笛卡尔积
    val rdd7 = rdd1.cartesian(rdd2)

    println(rdd7.collect().toList)

  }

  /**
    * cogroup: 类似 先对两个rdd实现groupByKey ，然后针对两个rdd 的分组结果执行全外连接
    */
  @Test
  def cogroup(): Unit ={

    val rdd1 = sc.parallelize(List("aa"->1,"cc"->2,"dd"->3,"ee"->4,"aa"->5))
    val rdd2 = sc.parallelize(List("aa"->10,"cc"->20,"tt"->30,"pp"->40))

    val rdd3 = rdd1.cogroup(rdd2)

    println(rdd3.collect().toList)

  }


}

class MyPartitioner(num:Int) extends Partitioner{
  /**
    * 返回重分区的分区数
    * @return
    */
  override def numPartitions: Int = {
    if(num<6)
      6
    else
      num
  }

  /**
    * 根据key获取分区号
    * @param key
    * @return
    */
  override def getPartition(key: Any): Int = key match {
    case 1 => 2
    case 2 => 5
    case 4 => 3
    case 3 => 1
    case _ => 0
  }
}
