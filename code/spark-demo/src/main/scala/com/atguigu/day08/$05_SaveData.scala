package com.atguigu.day08

import java.util.Properties

import org.apache.spark.sql.{Dataset, SaveMode}
import org.junit.Test

class $05_SaveData {

  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._

  /**
    * 数据保存的两种方式:
    *     1、df/ds.write
    *           .mode(SaveMode.XXX) --指定写入模式
    *           .format(csv/json/parquet/jdbc/orc/text ) --指定数据保存格式
    *           .option(...)...  --指定数据保存需要的参数
    *           .save([path]) --保存
    *    2、df/ds.write.mode(SaveMode.XX).option(..)...json/text/parquet/orc/csv...[常用]
    *
    *  SaveMode.Append： 如果数据保存的目录/表已经存在则追加数据
    *  SaveMode.Overwrite: 如果数据保存的目录/表已经存在则覆盖目录中的数据/表中的数据 <一般用于将数据写入HDFS>
    */
  @Test
  def saveAsFile(): Unit ={

    val df = spark.read.option("header","true").option("inferSchema","true").csv("datas/presidential_polls.csv")

    //保存为json
    //df.write.mode(SaveMode.Overwrite).json("output/json")

    //保存为parquet
    //df.write.mode(SaveMode.Overwrite).parquet("output/parquet")

    //保存为csv
    df.write.option("header","true").option("sep","\t")
      .csv("output/csv")

    //保存为文本
//    val jSON: Dataset[String] = df.toJSON
    df.toJSON.write.mode(SaveMode.Overwrite).text("output/text")

    //保存到mysql
    val props = new Properties()
    props.setProperty("user","root")
    props.setProperty("password","123456")
    df.write.mode(SaveMode.Overwrite).jdbc("jdbc:mysql://hadoop102:3306/test","presidential_polls",props)

    df.rdd.foreachPartition(
      it=>{
        //...

      }
    )
  }
}
