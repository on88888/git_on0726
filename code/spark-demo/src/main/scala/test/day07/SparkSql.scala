package test.day07

import org.junit.Test

/**
 * @Author 0726
 * @ClassName SparkSql
 * @createTime 2021年11月08日 14:16
 */
class SparkSql {
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._

  @Test
  def command(): Unit ={

    val ds = spark.read.textFile("datas/agent.log")

    val df = ds.map(line=>{
      val arr = line.split(" ")
      (arr.head.toLong,arr(1).toInt,arr(2).toInt,arr(3).toInt,arr(4).toInt)
    }).toDF("timestr","pro","city","userid","adid")

    val df5 = List((1,"zhangsan",20,"beijing"),(2,"lisi",30,"shenzhen"),(2,"wangwu",30,"shenzhen"),(1,"zhangsan",20,"beijing"))
      .toDF("id","name","age","address")

    val df6 = df5.distinct()

    df6.show

    val df7 = df5.dropDuplicates("id")
    df7.show
  }

  @Test
  def operator(): Unit ={

    val ds = spark.read.textFile("datas/agent.log")

    val df = ds.map(line=>{
      val arr = line.split(" ")
      (arr.head.toLong,arr(1).toInt,arr(2).toInt,arr(3).toInt,arr(4).toInt)
    }).toDF("timestr","pro","city","userid","adid")

    //需要将数据集注册成表
    df.createOrReplaceTempView("person")
    //    df.createOrReplaceGlobalTempView("person")

    //写sql
    spark.sql(
      """
        |select * from person where pro=6
      """.stripMargin).show

/*    val spark2 = spark.newSession()

    spark2.sql(
      """
        |select * from global_temp.person where pro=5
        |""".stripMargin).show*/

  }

}
