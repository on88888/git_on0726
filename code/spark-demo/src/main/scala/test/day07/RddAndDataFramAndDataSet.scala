package test.day07

import com.atguigu.day07.Person
import org.junit.Test

/**
 * @Author 0726
 * @ClassName RddAndDataFramAndDataSet
 * @createTime 2021年11月08日 14:44
 */
class RddAndDataFramAndDataSet {
  @Test
  def rdd_DF_DS(): Unit ={
    import org.apache.spark.sql.SparkSession
    val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
    import spark.implicits._

    val list = List( Person("zhangsan",20,"shenzhen"),Person("wangwu",33,"beijing"),Person("zhaoliu",25,"shenzhen") )
    val rdd = spark.sparkContext.parallelize(list)

    //1、rdd转dataframe: rdd.toDF()/ rdd.toDF(...)
    val df = rdd.toDF()

    //2、rdd转dataset: rdd.toDS
    val ds = rdd.toDS()

    //3、dataFrame转rdd: df.rdd
    val rdd2 = df.rdd

    //4、dataset转rdd: ds.rdd
    val rdd3 = ds.rdd

    //5、dataset转dataframe: ds.toDF/ds.toDF(...)
    val df2 = ds.toDF()

    /**
     * 6、dataframe转dataset: df.as[行类型]
     * dataframe转dataset的时候as里面的行类型可以写样例类与元组:
     * 如果类型写的是元组，元组的元素个数必须与列的个数一致,类型也必须一致或者是能够自动转换的类型
     * 如果类型写的是样例类，样例类如有属性,属性名必须与列名<保持一致>[属性可以不写]
     */
    val ds3 = df.as[Student]
    ds3.show()


  }
}
//样例类
case class Student(name:String,age:Int,address:String)