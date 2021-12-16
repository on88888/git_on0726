package com.atguigu.day08

object $06_SparkHive {

  def main(args: Array[String]): Unit = {

    System.setProperty("HADOOP_USER_NAME","atguigu")
    import org.apache.spark.sql.SparkSession
    val spark = SparkSession.builder()
      .appName("test")
      .master("local[4]")
      .enableHiveSupport() //开启hive支持
      .getOrCreate()
    import spark.implicits._
    //读取hive表数据
    spark.sql("select * from stu_score").show
    spark.sql("select * from student").show

/*    //保存数据到hive
    spark.sql(
      """
        |insert into stu_score select * from stu_score
      """.stripMargin)*/

    //spark.sql("load data inpath ...")
  }
}
