package com.atguigu.day06

object Test3 {

  /**
   * *  一个job中多个stage的执行是串行
   * *  一个stage中多个task的执行是并行
   *
   * //数据量大使用reduceByKey
   * //数据量小使用累加器
   *
   * @param args
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

    //创建累加器对象
    val acc = new Top10Accumulator

    //注册
    sc.register(acc,"top10")


    rdd3.foreach(x=> acc.add(x))

    //得到统计结果
    val totalMap = acc.value

    //排序取前十
    val top10 = totalMap.toList.sortBy(x=>x._2).reverse.take(10)

    top10.foreach(println)

    Thread.sleep(1000000)
  }
}
