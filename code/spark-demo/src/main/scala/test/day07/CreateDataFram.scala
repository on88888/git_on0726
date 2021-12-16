package test.day07

import org.apache.spark.sql.DataFrame
import org.junit.Test

/**
 * @Author 0726
 * @ClassName CreateDataFram
 * @createTime 2021年11月08日 13:28
 */

//样例类
case class Person(name:String,age:Int,address:String)

class CreateDataFram {
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._

  @Test
  def createDataFramByToDF(): Unit ={
    val list = List(Person("lisi",20,"shenzhen"),Person("zhangsan",22,"guangzhou"),Person("wangwu",16,"shanghai"))
    val df = list.toDF()
    df.printSchema()
    df.show()

    val list2 = List( ("zhangsan",20,"shenzhen"),("wangwu",33,"beijing"),("zhaoliu",25,"shenzhen") )
    val df2 = list2.toDF("name","AGE","address")
    df2.show()

    val add = spark.sparkContext.parallelize(list)
    val df4 = add.toDF()
    df4.show()

  }

  @Test
  def createDataFramByFile(): Unit ={
    val df = spark.read.json("datas/df.json")
    df.show()

    val df2 = spark.read.textFile("datas/wc.txt")
    df2.show()
  }

  @Test
  def createDataFromByDataFram(): Unit ={

    val df = spark.read.json("datas/df.json")

    val df2:DataFrame = df.where("age>19 and age<21")
    val df3 = df2.selectExpr("name","age")

    df2.show()
  }






























}
