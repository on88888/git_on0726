package test.day06

import org.apache.spark.rdd.RDD

import scala.collection.mutable.ArrayOps

/**
 * @Author 0726
 * @ClassName Test5_2
 * @createTime 2021年11月04日 10:40
 */
object Test5_2 {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //2、读取数据
    val rdd1: RDD[String] = sc.textFile("datas/user_visit_action.txt")
    //3、过滤[过滤掉搜索行为数据]
    val rdd2 = rdd1.filter(x=>{
      val arr: Array[String] = x.split("_")
      arr(5)=="null"
    })//.collect().toList.foreach(println)
    //4、切割、炸开、数据转换
    val rdd3 = rdd2.flatMap(x=>{
      //切割
      val arr= x.split("_")
      val dj = arr(6)
      val xd = arr(8)
      val zf = arr(10)
        //点击行为数据
      if (dj!="-1"){
        (dj,(1,0,0))::Nil
        //下单行为
      }else if (xd!="null"){
        xd.split(",").map(id=>(id,(0,1,0)))
        //支付行为
      }else {
        zf.split(",").map(id=>(id,(0,0,1)))
      }
    })//.collect().toList.foreach(println)

    //5、按照品类id聚合
    val rdd4: RDD[(String, (Int, Int, Int))] = rdd3.reduceByKey((agg,curr)=>(agg._1+curr._1,agg._2+curr._2,agg._3+curr._3))
    //6、排序取前十
    val result = rdd4.sortBy(x=>(x._2._1,x._2._2,x._2._3),false).take(10)
    //7、结果展示
    result.foreach(println)

    Thread.sleep(1000000)
  }
}
