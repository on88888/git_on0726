package com.atguigu.day07

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row}
import org.junit.Test

case class Person(name:String,age:Int,address:String)

class $04_CreateDataFrame {

  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()

  /**
    * DataFrame的创建方式:
    *     1、读取文件创建DataFrame
    *     2、通过toDF方法创建
    *     3、其他dataFrame衍生
    */

  /**
    * 1、通过toDF方法创建
    *     集合.toDF
    *     rdd.toDF
    *
    *     如果集合/rdd中的元素是样例类,通过toDF转成dataFrame的时候,dataFrame的列名默认就是样例类的属性名
    *     如果集合/rdd中的元素是元组,通过toDF转成dataFrame的时候,dataFrame的列名默认是 _N,如果想要重定义列名可以使用有参的toDF方法
    *
    */
  @Test
  def createDataFrameByToDF(): Unit ={
                                    //Person--样例类  case class Person(属性名：属性,......)
    val list = List( Person("zhangsan",20,"shenzhen"),Person("wangwu",33,"beijing"),Person("zhaoliu",25,"shenzhen") )

    import spark.implicits._
    val df = list.toDF
    df.printSchema()
    df.show()
                                                          //元组
    val list2 = List( ("zhangsan",20,"shenzhen"),("wangwu",33,"beijing"),("zhaoliu",25,"shenzhen") )
    //反射
    val df2 = list2.toDF("name","age","address")
    df2.show

    val rdd = spark.sparkContext.parallelize(list)

    val df4 = rdd.toDF()
    df4.show
  }

  @Test
  def createDataFrameByFile(): Unit ={

    val df = spark.read.json("datas/df.json")

    df.show
  }

  @Test
  def createDataFrameByDataFrame(): Unit ={

    val df: DataFrame = spark.read.json("datas/df.json")

    val df2: DataFrame = df.where("age>20  and age<=30")

    val df3 = df2.selectExpr("name","age")
    df2.show
    df3.show
  }

  @Test
  def createDataFrameByMethod(): Unit ={
    val list = List( Person("zhangsan",20,"shenzhen"),Person("wangwu",33,"beijing"),Person("zhaoliu",25,"shenzhen") )
    val df = spark.createDataFrame(list)

    df.show

    val rdd = spark.sparkContext.parallelize(List(
      Row( "lisi1",20,"shenzhen1" ),
      Row( "lisi2",21,"shenzhen2" ),
      Row( "lisi3",22,"shenzhen3" )
    ))

    val fields = Array( StructField("name",StringType) ,StructField("age",IntegerType),StructField("address",StringType)  )
    val schema = StructType( fields  )

    val df2 = spark.createDataFrame(rdd,schema)
    df2.show
  }
}
