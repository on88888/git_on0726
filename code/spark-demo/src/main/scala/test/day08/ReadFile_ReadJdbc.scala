package test.day08

import java.util.Properties

import org.apache.spark.sql.SaveMode
import org.junit.Test

/**
 * @Author 0726
 * @ClassName LoadData_ReadJdbc
 * @createTime 2021年11月08日 17:05
 */
class ReadFile_ReadJdbc {
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._

  @Test
  def readFile(): Unit ={
    //读取文本文件
    spark.read.textFile("datas/wc.txt").show()
    //读取json文件
    spark.read.json("datas/df.json").show()
    //保存为parquet文件
    spark.read.json("datas/df.json").write.mode(SaveMode.Overwrite).parquet("output/parquet")
    //读取parquet文件
    spark.read.parquet("output/parquet").show()
    //读取csv文件
      //csv文件常用的option(选项)参数
      //  sep:设置字段之间的分隔符(默认为 , )
      //  header:是否以第一行作为列名 (默认为false)
      //  inferSchema: 是否自动推断类型 (默认为false)
    spark.read.option("header","true").option("inferSchema","true").csv("datas/presidential_polls.csv").printSchema()
  }

  @Test
  def readJdbc(): Unit ={

    val url = "jdbc:mysql://hadoop102:3306/gmall"
    val tableName = "user_info"

    val props = new Properties()
    props.setProperty("user","root")
    props.setProperty("password","123456")

    //第一种读取sql方式：此方式读取只有一个分区【用于小数据量查询】
    val df = spark.read.jdbc(url, tableName, props)
    //df.write.mode(SaveMode.Overwrite).json("output/tab_json")  //保存为json文件
    //查看分区数
    //println(df.rdd.getNumPartitions)

    //第二种方式读取mysql: 此种方式读取mysql的时候分区数 = conditions里面的条件个数 <不常用>
    val conditions = Array("id<25","id>30 and id<40")
    val df2 = spark.read.jdbc(url,tableName,conditions,props)
    //df2.write.mode(SaveMode.Overwrite).json("output/tab2_json")

    /**
     * <常用>
     * <第三种读取sql方式>：此方式读取mysql的分区数为 numPartitions > (uppwerBound-lowerBound) : uppwerBound-lowerBound : numPartitions <常用>
     * columnName: 用于分区的列名,必须是数字、日期、时间戳类型的列
     * lowerBound: 用于规划分区的列的下限
     * upperBound: 用于规划分区的列的上限
     * numPartitions: 分区数
     */
    val minmaxDF = spark.read.jdbc(url,"(select min(id) minid,max(id) maxid from user_info) max_min_id",props)
    val rows = minmaxDF.rdd.collect()
    val lowerBound = rows(0).getAs[Long]("minid")
    val upperBound = rows(0).getAs[Long]("maxid")
    //下界（下限）----上界（上限）  [数据量]
    println(s"${lowerBound}----${upperBound}")
    val df3 = spark.read.jdbc(url,tableName,"id",lowerBound,upperBound,10,props)
//    df3.write.mode(SaveMode.Overwrite).csv("output/tab3_csv")
  }
}
