package test.c2

import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName $05_RDDxuliuhua
 * @createTime 2021年11月02日 10:16
 */
object $05_RDDxuliuhua {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[Int] = sc.parallelize(List(10,3,2,5,7,8))

    val x = 100

    val rdd2: RDD[Int] = rdd1.map(a => a * x)

    println(rdd2.collect().toList)


  }

  val a = 10
  //闭包: 函数体中使用了外部变量的函数
  val func = (x:Int) => x + a

}
