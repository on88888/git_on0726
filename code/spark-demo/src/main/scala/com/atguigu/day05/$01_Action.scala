package com.atguigu.day05

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.rdd.RDD
import org.junit.Test

class $01_Action {

  import org.apache.spark.{SparkConf, SparkContext}
  val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

  /**
    * reduce: reduce是对rdd所有元素聚合
    *   reduce是先对每个分区所有数据聚合,将每个分区的聚合结果发给Driver,由Driver统一汇总
    */
  @Test
  def reduce(): Unit ={

    val rdd = sc.parallelize(List(3,2,6,5,1,10),2)

    val r = rdd.reduce( (agg,curr)=>{
      println(s"${Thread.currentThread().getName} --agg=  ${agg}  curr= ${curr}")
      agg+curr
    } )

    println(r)
  }

  /**
    * collect: 将rdd每个分区的数据收集到Driver端口
    *     如果rdd分区数据量比较大,而Driver内存默认只有1G,所以可能出现内存溢出
    *     在工作中一般都需要调整Driver的内存大小,一般调整为5-10G.
    *     在spark-submit的是可以通过--driver-memory调整Driver内存大小
    */
  @Test
  def collect(): Unit ={
    val rdd = sc.parallelize(List(3,2,6,5,1,10),2)

    println(rdd.collect().toList)
  }

  /**
    * count: 统计rdd元素个数
    */
  @Test
  def count(): Unit ={
    val rdd = sc.parallelize(List(3,2,6,5,1,10),2)

    println(rdd.count)
  }

  /**
    * first: 获取rdd第一个元素
    */
  @Test
  def first(): Unit ={
    val rdd = sc.parallelize(List(3,2,6,5,1,10),2)

    println(rdd.first())
  }

  /**
    * take: 获取前N个元素
    */
  @Test
  def take(): Unit ={
    val rdd = sc.parallelize(List(3,2,6,5,1,10),2)

    println(rdd.take(3).toList)
  }

  /**
    * takeOrdered: 对RDD排序之后取前N个元素
    */
  @Test
  def takeOrdered(): Unit ={
    val rdd = sc.parallelize(List(3,2,6,5,1,10),2)

    val r = rdd.takeOrdered(2)

    println(r.toList)
  }

  /**
    * fold(默认值)(func: (RDD元素类型,RDD元素类型)=> RDD元素类型): 对RDD所有元素聚合
    *     fold首先会对每个分区所有元素中进行聚合,然后将每个分区的聚合结果发给Driver进行汇总
    *           fold在每个分区中第一次计算的时候,函数第一个参数的初始值 = 默认值
    *           fold在Driver第一次计算的时候,函数第一个参数的初始值 = 默认值
    */
  @Test
  def fold(): Unit ={
    val rdd = sc.parallelize(List(3,2,6,5,1,10),2)

    val r = rdd.fold(-1)( (agg,curr)=>{
      println(s"${Thread.currentThread().getName} --agg=  ${agg}  curr= ${curr}")
      agg+curr
    })

    println(r)
  }

  /**
    * aggregate(默认值)(seqOp: (默认值类型,RDD元素类型)=> 默认值类型  , comop: ( 默认值类型,默认值类型 )=>默认值类型 ) : 对RDD所有元素聚合
    *     seqOp: 是在每个分区中对所有元素聚合逻辑
    *     comop： 在driver中对所有分区的聚合结果的汇总逻辑
    * fold与aggregate的区别:
    *     fold的分区内聚合逻辑与driver汇总逻辑一样
    *     aggregate的分区内聚合逻辑与driver汇总逻辑可以不一样
    *
    */
  @Test
  def aggregate(): Unit ={
    val rdd = sc.parallelize(List(3,2,6,5,1,10),2)

    val r = rdd.aggregate(-1)( (agg:Int,curr:Int)=>{
      println(s"分区内计算 ${Thread.currentThread().getName} --agg=  ${agg}  curr= ${curr}")
      agg+curr
    }, (agg,curr)=>{
      println(s"driver内的计算 ${Thread.currentThread().getName} --agg=  ${agg}  curr= ${curr}")
      agg+curr
    })

    println(r)
  }

  /**
    * countBykey: 统计每个key的个数
    *     countByKey一般结合sample一起使用
    *     后续如果出现了数据倾斜,一般先用sample采样数据,然后用countByKey统计采样结果,从而得知哪个key出现了数据倾斜
    */
  @Test
  def countByKey(): Unit ={

    val rdd = sc.parallelize(List( "aa"->1,"bb"->2,"aa"->10,"aa"->50,"cc"->30,"bb"->40 ))

    val map = rdd.countByKey()

    println(map)
  }

  @Test
  def save(): Unit ={
    val rdd = sc.parallelize(List( "aa"->1,"bb"->2,"aa"->10,"aa"->50,"cc"->30,"bb"->40 ),2)

    rdd.saveAsTextFile("output/text")
  }

  /**
    * foreach(func: RDD元素类型=>Unit): Unit
    *     foreach里面的函数是针对单个元素操作,元素有多少个,函数就调用多少次
    *     foreach是并行执行
    *     foreach与map的区别:
    *         map是转换算子,会生成一个新的RDD
    *         foreach是行动算子,不会生成新的RDD,会触发任务计算,得到具体的结果
    *
    */
  @Test
  def foreach(): Unit ={
    val rdd = sc.parallelize(List( "aa"->1,"bb"->2,"aa"->10,"aa"->50,"cc"->30,"bb"->40 ),2)

    rdd.foreach(x=> println(x))
    rdd.foreach{
      case (s,i) => println((s,i),1)
    }
  }

  /**
    * foreachPartition(func: Iterator[RDD元素类型]=>Unit):Unit
    *     foreachPartition里面的函数是针对每个分区操作,函数的参数是每个分区所有数据的迭代器,rdd有多少分区,函数就执行多少次。
    *     foreachPartition一般用于将数据保存到mysql,hbase,redis等存储介质中,可以减少资源链接的创建与销毁的次数。
    */
  @Test
  def foreachPartition(): Unit ={

    val rdd = sc.parallelize(List(
      (1,"zhagnsan",88.0),
      (2,"lisi",75.0),
      (3,"wangwu",66.6),
      (4,"zhaoliu",100.0)
    ),2)

    /*rdd.foreach(x=>{

      var connection:Connection = null
      var statement:PreparedStatement = null
      try{
        connection = DriverManager.getConnection(".....","","")
        statement = connection.prepareStatement("insert into person values(?,?,?)")
        statement.setInt(1,x._1)
        statement.setString(2,x._2)
        statement.setDouble(3,x._3)

        statement.executeUpdate()
      }catch {
        case e:Exception => e.printStackTrace()
      }finally {
        if(statement!=null)
          statement.close()
        if(connection!=null)
          connection.close()
      }

    })*/

    rdd.foreachPartition((it:Iterator[(Int,String,Double)])=> {
      var connection:Connection = null
      var statement:PreparedStatement = null
      try{
        connection = DriverManager.getConnection(".....","","")
        statement = connection.prepareStatement("insert into person values(?,?,?)")
        var i = 0
        it.foreach((x:(Int,String,Double))=>{
          statement.setInt(1,x._1)
          statement.setString(2,x._2)
          statement.setDouble(3,x._3)
          //加入一个批次中
          statement.addBatch()

          if(i%1000==0){
            statement.executeBatch()
            statement.clearBatch()
          }
          i = i+1
        })

        //最后不满1000条的批次处理
        statement.executeBatch()

      }catch {
        case e:Exception => e.printStackTrace()
      }finally {
        if(statement!=null)
          statement.close()
        if(connection!=null)
          connection.close()
      }
    } )


  }
}
