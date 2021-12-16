package com.atguigu.day07

import org.junit.Test

class $06_sparksql {
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._
  /**
    * sparksql代码编写方式有两种:
    *       1、命令式[select/where/filter等方法编写代码]
    *       2、声明式[sql语句编写代码]<常用>
    */

  /**
    * 1、命令式[select/where/filter等方法编写代码]
    *       列裁剪： selectExpr
    *       去重
    *         distinct: 两行数据所有列都相同才会去重
    *         dropDuplicates : 两行数据指定列的数据相同就算重复,会去重
    *       过滤: where/filter
    */
  @Test
  def command(): Unit ={

    val ds = spark.read.textFile("datas/agent.log")

    val df = ds.map(line=> {
      val arr = line.split(" ")
      (arr.head.toLong, arr(1).toInt,arr(2).toInt,arr(3).toInt,arr(4).toInt)
    } ).toDF("timestr","province","city","userid","adid")

    //列裁剪： selectExpr [可以写sql函数,可以定义别名,与sql语句里面的select写法完全一样]

    //import org.apache.spark.sql.functions._
    //val df2 = df.select(min($"adid"))
    //df2.show

    val df3 = df.selectExpr("min(adid) xx")
    //df3.show

    //select .. from t

    //去重
    //distinct: 两行数据所有列都相同才会去重
    //dropDuplicates : 两行数据指定列的数据相同就算重复,会去重
    //select distinct .... from ..

    val df5 = List((1,"zhangsan",20,"beijing"),(2,"lisi",30,"shenzhen"),(2,"wangwu",30,"shenzhen"),(1,"zhangsan",20,"beijing"))
      .toDF("id","name","age","address")

    val df6 = df5.distinct()

    //df6.show

    val df7 = df5.dropDuplicates("id")
    //df7.show

    //过滤: where/filter
    df.where("province=6").show
    df.filter("city=7").show

  }

  /**
    * 声明式
    *     createOrReplaceTempView: 创建临时表,如果表名已经存在则替换表。只能在当前sparksession使用 <常用>
    *     createOrGlobalReplaceTempView: 创建全局表,如果表名已经存在则替换表。可以在多个sparksession中使用,在使用的时候<表名前面必须加上global_temp的库名>
    */
  @Test
  def operator(): Unit ={


    val ds = spark.read.textFile("datas/agent.log")

    val df = ds.map(line=> {
      val arr = line.split(" ")
      (arr.head.toLong, arr(1).toInt,arr(2).toInt,arr(3).toInt,arr(4).toInt)
    } ).toDF("timestr","province","city","userid","adid")

    //需要将数据集注册成表
    //df.createOrReplaceTempView("person")
    df.createOrReplaceGlobalTempView("person")

    //写sql
    spark.sql(
      """
        |select * from global_temp.person where province=6
      """.stripMargin).show

    val spark2 = spark.newSession()

    spark2.sql(
      """
        |select * from global_temp.person where province=6
      """.stripMargin).show

  }
}
