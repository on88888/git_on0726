package com.atguigu.day07

import org.apache.spark.sql.{DataFrame, Dataset, Row}
import org.junit.Test

class $05_CreateDataSet {

  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._

  /**
    * DataSet创建方式:
    *     1、toDS方法创建
    *     2、读取文件创建
    *     3、其他dataset衍生
    */

  /**
    * 1、toDS方法创建
    *       集合.toDS
    *       rdd.toDS
    *       如果集合/rdd中的元素是样例类,通过toDS转成dataset的时候,dataset的列名默认就是样例类的属性名
    *      如果集合/rdd中的元素是元组,通过toDS转成dataset的时候,dataset的列名默认是 _N,如果想要重定义列名可以使用有参的toDF方法
    */
  @Test
  def createDataSetByToDS(): Unit ={

    val list = List( Person("zhangsan",20,"shenzhen"),Person("wangwu",33,"beijing"),Person("zhaoliu",25,"shenzhen") )

    val ds = list.toDS()

    ds.show()

    val list2 = List( ("zhangsan",20,"shenzhen"),("wangwu",33,"beijing"),("zhaoliu",25,"shenzhen") )

    val rdd = spark.sparkContext.parallelize(list2)

    val ds2: Dataset[Row] = rdd.toDS().toDF("name","age","address")

    ds2.show()

  }

  /**
    * 读取文件创建dataset
    */
  @Test
  def createDataSetByFile(): Unit ={

    val ds = spark.read.textFile("datas/wc.txt")
//    val frame: DataFrame = spark.read.json("datas/df.json")
    ds.show

  }

  /**
    * 通过其他dataset衍生
    */
  @Test
  def createDataSetByDataSet(): Unit ={
    val ds = spark.read.textFile("datas/wc.txt")

    val ds2 = ds.flatMap(line=> line.split(" "))

    val ds3 = ds2.map((_,1)).toDF("word","num")

    ds3.show
  }

  /**
    * 通过createDataSet方法创建
    */
  @Test
  def createDataSet(): Unit ={
    val list = List( Person("zhangsan",20,"shenzhen"),Person("wangwu",33,"beijing"),Person("zhaoliu",25,"shenzhen") )

    val ds = spark.createDataset(list)

    ds.show
  }
}
