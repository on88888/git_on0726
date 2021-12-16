package test.day06

import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName Test5_1
 * @createTime 2021年11月04日 09:30
 */
object Test5_1 {
  def main(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)
    //2、读取数据
    val rdd = sc.textFile("datas/user_visit_action.txt")
    //3、过滤[过滤掉搜索行为数据]
    val rdd2 = rdd.filter(x=>{
      val arr: Array[String] = x.split("_")
      arr(5)=="null"
    })//.collect().toList.foreach(println)
    //4、列裁剪【点击品类id,下单品类ids, 支付品类ids】
    //RDD((1,-1,NULL),(NULL,3,null),(NULL,-1, 4,6,10))
    val rdd3 = rdd2.map(x=>{
      val arr: Array[String] = x.split("_")
      var dj = arr(6)
      var xd =arr(8)
      var zf = arr(10)
      (dj,xd,zf)
    })//.collect().toList.foreach(println)

    //5、统计点击数
    //5.1、过滤出点击行为数据
    val djRdd = rdd3.filter{
      case (dj,xd,zf) => dj !="-1"
    }//.collect().toList.foreach(println)
    //5.2、列裁剪【点击id】
    val djId: RDD[(String, Int)] = djRdd.map(x=>(x._1,1))
    //5.3、按照点击id统计点击次数
    val djCs: RDD[(String, Int)] = djId.reduceByKey((agg,curr)=>agg+curr)
//    djCs.collect().toList.sortBy(_._2).foreach(println)
    //6、统计下单数
    //6.1、过滤出下单行为数据
    val xdRdd = rdd3.filter{
      case (dj,xd,zf)=>xd != "null"
    }//.collect().toList.foreach(println)
    //6.2、列裁剪+炸开
    val xdId: RDD[(String, Int)] = xdRdd.flatMap( x=>x._2.split(",").map(x=>((x),1)) )
//    xdId.collect().toList.foreach(println)
    //6.3、按照下单id统计下单次数
    val xdCs: RDD[(String, Int)] = xdId.reduceByKey(_+_)
//    xdCs.collect().toList.foreach(println)
    //7、统计支付数
    //7.1、过滤出支付行为数据
    val zfRdd: RDD[(String, String, String)] = rdd3.filter(x=>x._3!="null")

    //7.2、列裁剪+炸开
    val zfId: RDD[(String, Int)] = zfRdd.flatMap( x=>x._3.split(",").map(x=>(x,1)) )
//    zfId.collect().toList.foreach(println)
    //7.3、按照支付id统计支付次数
    val zfCs: RDD[(String, Int)] = zfId.reduceByKey((agg,curr)=>agg+curr)
//    zfCs.collect().toList.foreach(println)
    //8、join 得到每个品类的点击数、下单数、支付数
    val result = djCs.leftOuterJoin(xdCs).leftOuterJoin(zfCs)
    //result.collect().toList.foreach(println)
    //9、排序取前十
    val top10= result.collect().toList.sortBy(x=>(x._2._1._1,x._2._1._2,x._2._2)).reverse.map{
      case (spId,((djCs,xdCs),zfCs)) => (spId,djCs,xdCs.getOrElse(0),zfCs.getOrElse(0))
    }.take(10)
    // 10、结果展示
    top10.toList.foreach(println)

    Thread.sleep(1000000)
  }
}
