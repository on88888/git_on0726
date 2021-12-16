package com.atguigu.day08

object $01_DataFrameDataSet {

  /**
    * DataFrame与DataSet的使用场景：
    *     1、如果是要将rdd/集合转成sparksql操作
    *         1、此时rdd/集合元素类型如果是元组,此时推荐转成dataframe, 可以通过toDF有参方法重定义列名
    *         2、此时rdd/集合元素类型如果是样例类,此时随便转成dataframe或者dataset都可以
    *     2、如果想要使用map/flatMap这种写函数的强类型算子,此时推荐使用dataset。
    * Row类型取值: row.getAs[列的类型](列名)
    *
    */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.sql.SparkSession
    val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
    import spark.implicits._
    val list2 = List( ("zhangsan",20,"shenzhen"),("wangwu",33,"beijing"),("zhaoliu",25,"shenzhen") )

    val rdd = spark.sparkContext.parallelize(list2)
    // 1、如果是要将rdd/集合转成sparksql操作
    val df = rdd.toDF("NAME","AGE","ADDRESS")

    df.createOrReplaceTempView("user")

    spark.sql("select NAME,AGE,ADDRESS from user").show

    //
    val ds = df.map(row=> row.getAs[String]("NAME"))

    //如果想要使用map/flatMap这种写函数的强类型算子,此时推荐使用dataset。
    val ds2 = rdd.toDS()

    val ds3 = ds2.map(_._1)
    ds.show
    ds3.show
  }
}
