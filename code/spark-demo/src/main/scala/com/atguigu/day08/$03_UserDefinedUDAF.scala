package com.atguigu.day08

object $03_UserDefinedUDAF {
  /**
   * UDAF:多进一出 （聚合函数）
   * @param args
   */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.sql.SparkSession
    val spark = SparkSession.builder().appName("test").master("local[4]")
//      .config("spark.sql.shuffle.partitions","2")
      .getOrCreate()
    import spark.implicits._

    val ds = spark.read.textFile("datas/agent.log")

    val df = ds.map(line=>{
      val arr = line.split(" ")
      (arr(1),arr.last)
    }).toDF("province","adid")

    df.createOrReplaceTempView("user_ad")

    df.rdd.mapPartitionsWithIndex((index,it)=>{
      println(s"index:${index} data=${it.toList}")
      it
    }).collect()
   // spark.sql("select province,avg(adid) from user_ad group by province").show
    //注册udaf函数[弱类型]
    spark.udf.register("myavg",new MyAvgUDAF1)
    //注册udaf函数[强类型]
    import org.apache.spark.sql.functions._
    val function = udaf(new MyAvgUDAF2)
    spark.udf.register("myavg2",function)
    //使用
    val df3 = spark.sql("select province,myavg(adid) from user_ad group by province")
    val df4 = spark.sql("select province,myavg2(adid) from user_ad group by province")

    df3.show
    df4.show
    println(df3.rdd.getNumPartitions)
  }
}
