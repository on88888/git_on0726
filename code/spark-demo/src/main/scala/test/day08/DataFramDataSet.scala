package test.day08

/**
 * @Author 0726
 * @ClassName DataFramDataSet
 * @createTime 2021年11月08日 14:54
 */
object DataFramDataSet {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.sql.SparkSession
    val spark = SparkSession.builder().appName("test").master("local[4]").getOrCreate()
    import spark.implicits._

    val list = List(("lisi",33,"shenzhen"),("wangwu",23,"guangzhou"),("zhaoliu",25,"beijing"))
    val rdd = spark.sparkContext.parallelize(list)

    val df = rdd.toDF("name","age","city")
//    df.show()
    df.createOrReplaceTempView("user")

    spark.sql(
      """
        |select age,city from user
        |""".stripMargin).show

    val ds = df.map(row=>row.getAs[String]("name"))
    ds.show()

    //如果想要使用map/flatMap这种写函数的强类型算子,此时推荐使用dataset。
    val ds2 = rdd.toDS()
    val ds3 = ds2.map(_._1)
    ds3.show()
  }
}
