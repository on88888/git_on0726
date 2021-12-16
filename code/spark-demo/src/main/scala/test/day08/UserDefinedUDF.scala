package test.day08

/**
 * @Author 0726
 * @ClassName UserDefinedUDF
 * @createTime 2021年11月08日 15:34
 */
object UserDefinedUDF {
  def main(args: Array[String]): Unit = {

    import org.apache.spark.sql.SparkSession
    val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
    import spark.implicits._

    val list = List( ("001","lisi"),("002","wuwang"),("003","zhangda") )

    val df = list.toDF("id","name")
//    df.show()

//    val pri = (id:String)=>("0"*(8-id.length) + id)
//    spark.udf.register("myprfix",pri)
    spark.udf.register("myprfix",prifixid _)

    df.selectExpr("myprfix(id) id","name").show

//    Thread.sleep(100000)

  }
  def prifixid(id:String):String = {
    "0"*(8-id.length) + id
  }
}
