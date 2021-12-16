package com.atguigu.day08

import java.util.Properties

import org.apache.spark.sql.SaveMode
import org.junit.Test

class $04_LoadData {

  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._

  /**
    * spark读取数据有两种方式:
    *     1、spark.read
    *         .format("csv/json/text/jdbc/parquet/orc") --指定数据格式
    *         .option(k,v) --指定读取时需要的参数名与参数值
    *         .....
    *         .load([path]) --加载数据
    *    2、spark.read.option(...)....csv/json/parquet/orc/jdbc/textFile <常用>
    */

  @Test
  def readFile(): Unit ={

    //读取文件文件
    spark.read.textFile("datas/wc.txt").show

    //读取json数据
    spark.read.json("datas/df.json").show
    spark.read.json("datas/df.json").write.mode(SaveMode.Overwrite).parquet("output/parquet")
    //读取parquet数据
    spark.read.parquet("output/parquet").show

    //读取csv
    // csv文件常用的option参数
    //      sep: 字段之间的分隔符(默认为 , )
    //      header: 是否使用第一行作为列名 （默认为false）
    //      inferSchema: 是否自动推断列的类型
    spark.read.option("header","true").option("inferSchema","true").csv("datas/presidential_polls.csv").printSchema()
  }

  @Test
  //常用（需要熟悉）
  def readJdbc(): Unit ={

    val url = "jdbc:mysql://hadoop102:3306/gmall"

    val tableName = "user_info"

    val props = new Properties()
    props.setProperty("user","root")
    props.setProperty("password","123456")

    //第一种读取mysql方式: 此种方式读取mysql的时候只有一个分区 【只用于小数据量查询】
    val df = spark.read.jdbc(url,tableName,props)
//    df.show()
    println(df.rdd.getNumPartitions)

    //第二种方式读取mysql: 此种方式读取mysql的时候分区数 = conditions里面的条件个数 <不常用>
    val conditions = Array("id<25","id>=25 and id<50","id>=50 and id<75","id>=75")
    spark.read.jdbc(url,tableName,conditions,props)//.write.mode(SaveMode.Overwrite).csv("output/csv")

    //第三种方式读取mysql: 此种范式读取mysql的分区数为  numPartitions > (uppwerBound-lowerBound) : uppwerBound-lowerBound : numPartitions <常用>
    //  columnName: 用于分区的列名,必须是数字、日期、时间戳类型的列
    //  lowerBound: 用于规划分区的列的下限
    //  upperBound: 用于规划分区的列的上限
    //  numPartitions: 分区数
    val minmaxDf = spark.read.jdbc(url,"(select min(id) minid,max(id) maxid from user_info) max_min_id",props)
    val rows = minmaxDf.rdd.collect()
    val lowerBound = rows(0).getAs[Long]("minid")
    val upperBound  = rows(0).getAs[Long]("maxid")
    println(s"${lowerBound} -- ${upperBound}")
    spark.read.jdbc(url,tableName,"id",lowerBound,upperBound,10,props).write.mode(SaveMode.Overwrite).csv("output/csv1")
  }
}
