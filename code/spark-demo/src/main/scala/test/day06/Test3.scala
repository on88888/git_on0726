package test.day06

import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName Test3
 * @createTime 2021年11月03日 18:32
 */
object Test3 {

  /**
   * 累加器
   * @param args
   */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)
    //创建自定义累加器对象
    val acc = new WCAccumulator_Test3
    //注册累加器
    sc.register(acc,"Test3acc")



    val rdd1 = sc.textFile("datas/wc.txt")

    val rdd2: RDD[(String, Int)] = rdd1.flatMap(_.split(" ")).map(x=>(x,1))

    rdd2.foreach(x=>acc.add(x))

    println(acc.value)

    Thread.sleep(100000)

//    println(rdd2.collect.toList)

//    val a = Map("hello"->1)
//    println(a.get("hello")) //    Some(1)
//    println(a.get("hello").get) //    1

  }
}
