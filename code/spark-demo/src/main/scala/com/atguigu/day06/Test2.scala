package com.atguigu.day06

object Test2 {
  /**
   *                                                                       6:点击品类id   8:下单品类ids    10:支付品类ids
   *                                                                       7:产品id       9:下单产品ids    11:支付产品ids
   *     0        1        2                              3      4               5    6  7   8   9    10   11  12
   * 2019-07-17_95_26070e87-1ad7-49a3-8fb3-cc741facaddf_37_2019-07-17 00:00:02_手机_-1_-1_null_null_null_null_3
   */
  def main(args: Array[String]): Unit = {

    //1、创建sparkcontext
    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    //2、读取数据
    val rdd1 = sc.textFile("datas/user_visit_action.txt")

    //3、过滤[过滤掉搜索行为数据]
    val rdd2 = rdd1.filter(line=>{
      val arr = line.split("_")
      arr(5)=="null"
    })

    //4、切割、炸开、数据转换
    val rdd3 = rdd2.flatMap(line=>{
      //切割
      val arr = line.split("_")

      val clickid = arr(6)

      val orderids = arr(8)

      val payids = arr(10)
      //点击行为数据
      if( clickid!="-1" ){
        (clickid, (1, 0,0) ) :: Nil
        //下单行为
      }else if( orderids !="null"){
        orderids.split(",").map(id=> (id,(0,1,0) ))
        //支付行为
      }else{
        payids.split(",").map(id=> (id,(0,0,1) ))
      }

    })
    //RDD(
    //    (A,(1,0,0)), (A,(0,1,0) , (B,(0,0,1)) ,(C,(0,1,0)), (B,(0,0,1)) ...
    // )
    //5、按照品类id聚合
    val rdd4 = rdd3.reduceByKey( (agg,curr)=> (agg._1+curr._1, agg._2+curr._2, agg._3+curr._3))
    //6、排序取前十
    val result = rdd4.sortBy(_._2,false).take(10)
    //7、结果展示
    result.foreach(println)

    Thread.sleep(10000000)
  }
}
