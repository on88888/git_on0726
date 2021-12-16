package test.day06

import org.apache.spark.rdd.RDD

import scala.collection.mutable
import scala.collection.mutable.ArrayOps

/**
 * @Author 0726
 * @ClassName Test5_3
 * @createTime 2021年11月04日 11:05
 */
object Test5_3 {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //导入数据
    val rdd1: RDD[String] = sc.textFile("datas/user_visit_action.txt")
    //3、过滤[过滤掉搜索行为数据]
    val rdd2: RDD[String] = rdd1.filter(_.split("_").apply(5) == "null")
//    rdd2.collect().toList.foreach(println)
    //4、切割、炸开、数据转换
    val rdd3 = rdd2.flatMap(x=>{
      val arr = x.split("_")
      val dj = arr(6)
      val xd = arr(8)
      val zf = arr(10)

      if (dj!="-1"){
        (  dj,(1,0,0) ) :: Nil
      }else if(xd!="null"){
        xd.split(",").map( id=>(id,(0,1,0)) )
      }else{
        zf.split(",").map( id=>(id,(0,0,1)) )
      }
    })//.collect().toList.foreach(println)

    //创建累加器对象
    val acc = new Accumulator_Test5_3
    //注册
    sc.register(acc,"t_top10")

    rdd3.foreach(x=>acc.add(x))
    //得到统计结果
    val countMap = acc.value
    //排序取前十
    val top10 = countMap.toList.sortBy(x=>x._2).reverse.take(10)
    //展示结果
    top10.foreach(println)

    Thread.sleep(1000000)
  }
}
