package test.day08

import java.util.Properties

import org.apache.spark.sql.{Dataset, SaveMode}
import org.junit.Test

/**
 * @Author 0726
 * @ClassName SaveData
 * @createTime 2021年11月08日 17:56
 */
class SaveData {
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
  import spark.implicits._

  @Test
  def saveFile(): Unit ={
    //读取数据
    val df = spark.read.option("header","true").option("inferSchema","true").csv("datas/presidential_polls.csv")
    //df.show()

    //保存为json
    //df.write.mode(SaveMode.Overwrite).json("output/json")

    //保存为parquet
    //df.write.mode(SaveMode.Overwrite).parquet("output/parquet")

    //保存为csv
    /*df.write.option("header","true").option("sep","\t")
      .csv("output/csv")*/

    //保存为文本
    //val jSON: Dataset[String] = df.toJSON
    //df.toJSON.write.mode(SaveMode.Overwrite).text("output/text")

    //保存到MySQL
    val url = "jdbc:mysql://hadoop102:3306/test"
    val tableName = "presidential_polls"
    val props = new Properties()
    props.setProperty("user","root")
    props.setProperty("password","123456")

    df.write.mode(SaveMode.Overwrite).jdbc(url,tableName,props)

  }
}
