package com.atguigu.day08

object $02_UserDefinedUDF {

  /**
   * UDF:一进一出 （一行数据一个结果）
    * 自定udf函数:
    *     1、定义一个方法/函数
    *     2、将定义的方法/函数在sparksession中注册: spark.udf.register("函数名",函数对象)
    */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.sql.SparkSession
    val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
    import spark.implicits._

    val list = List( ("1001","lisi"),("10030","wangwu"),("0020","zhaoliu"),("00000115","韩梅梅") )

    val df = list.toDF("id","name")

   // df.createOrReplaceTempView("user")

    spark.udf.register("myprfix",prfixid _)

    df.selectExpr("myprfix(id) id","name").show

  }

  def prfixid( id:String ):String = {
      "0"* (8-id.length) + id
  }
}
