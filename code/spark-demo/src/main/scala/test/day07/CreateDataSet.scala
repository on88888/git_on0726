package test.day07

import org.apache.spark.sql.DataFrame
import org.junit.Test

/**
 * @Author 0726
 * @ClassName CreateDataSet
 * @createTime 2021年11月08日 14:06
 */
//case class Person(name:String,age:Int,address:String)

class CreateDataSet {
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._

  @Test
  def createDataSetByToDS(): Unit ={
    val list = List(Person("lisi",20,"shenzhen"),Person("zhangsan",23,"guangzhou"),Person("wangwu",25,"wuhan"))

    val ds = list.toDS()
    ds.show()

    val ds1 = spark.createDataset(list)
  }

  @Test
  def createDataSetByDataSet(): Unit ={
    val ds = spark.read.textFile("datas/wc.txt")
    val ds2: DataFrame = ds.flatMap(_.split(" ")).map((_,1)).toDF("word","num")
    ds2.show()
  }
}
