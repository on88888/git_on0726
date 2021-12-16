package test.day06

import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName Test3_1
 * @createTime 2021年11月03日 18:45
 */
object Test3_1 {
  def main(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[Int] = sc.parallelize(List(10,20,30,40,50))

    val r: Int = rdd1.reduce((agg,curr)=>agg+curr)

    println(r)

    Thread.sleep(1000000)


  }
}
