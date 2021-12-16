package test.c2

import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName $04_Test
 * @createTime 2021年11月01日 20:05
 */
object $04_Test {
  /**
   * 统计每个省份点击量最多的三个广告
   */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[String] = sc.textFile("datas/agent.log")
    //分割字段,保留省份,广告
    val rdd2 = rdd1.map(x=>{
      val arr: Array[String] = x.split(" ")
      ( (arr(1),arr.last),1 )
    })

    //根据key聚合
//    val rdd3 = rdd2.groupBy(x=>x._1._1)
    val rdd3 = rdd2.reduceByKey(_+_)
    println(rdd3.collect().toList)

    //按照省份字段分组
    val rdd4 = rdd3.groupBy(x=>x._1._1)

    //转toList,排序(倒序),取前三,重组字段
    val rdd5 = rdd4.map(x=>{
      val top3 = x._2.toList.sortBy(x=>x._2).reverse.take(3).map{
        case ( (pro,ad),num )=>(ad,num)
      }
      (x._1, top3)
    })

    //展示结果
    rdd5.collect().toList.foreach(println)






  }
}
