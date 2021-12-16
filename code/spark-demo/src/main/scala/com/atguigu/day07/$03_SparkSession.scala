package com.atguigu.day07

object $03_SparkSession {
  /**
    * sparksession创建
    * @param args
    */
  def main(args: Array[String]): Unit = {
    //第一种方式
    //val spark = new SparkSession.Builder().appName("test").master("local[4]").getOrCreate()

    //第二种方式
    import org.apache.spark.sql.SparkSession
    val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()

  }
}
