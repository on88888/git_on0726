package com.atguigu.day06

object Test1 {

  /**
    * TOP10热门品类
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

    //4、列裁剪【点击品类id,下单品类ids, 支付品类ids】
    //RDD((1,-1,NULL),(NULL,3,null),(NULL,-1, 4,6,10))
    val rdd3 = rdd2.map(line=>{
      val arr = line.split("_")
      (arr(6), arr(8), arr(10))
    })
    //5、统计点击数
    //5.1、过滤出点击行为数据
    val clickFilterRdd = rdd3.filter{
      case (clickid,orderids,payids) => clickid!="-1"
    }

    //5.2、列裁剪【点击id】
    val clickRdd = clickFilterRdd.map(x=> (x._1,1) )

    //5.3、按照点击id统计点击次数
    val clickNumRdd = clickRdd.reduceByKey(_+_)

    //6、统计下单数
    //6.1、过滤出下单行为数据
    val orderFilterRdd = rdd3.filter{
      case (clickid,orderids,payids) => orderids!="null"
    }

    //6.2、列裁剪+炸开
    val orderRdd = orderFilterRdd.flatMap(x=> x._2.split(",").map((_,1)) )

    //6.3、按照下单id统计下单次数
    val orderNumRdd = orderRdd.reduceByKey(_+_)

    //7、统计支付数
    //7.1、过滤出支付行为数据
    val payFilterRdd = rdd3.filter{
      case (clickid,orderids,payids) => payids!="null"
    }

    //7.2、列裁剪+炸开
    val payRdd = payFilterRdd.flatMap(x=> x._3.split(",").map( (_,1) ) )
    //7.3、按照支付id统计支付次数
    val payNumRdd = payRdd.reduceByKey(_+_)

    //8、join 得到每个品类的点击数、下单数、支付数
    val totalNumRdd = clickNumRdd.leftOuterJoin( orderNumRdd ).leftOuterJoin(payNumRdd)
    //9、排序取前十
    val totalRdd = totalNumRdd.map{
      case (id,( (clickNum, orderNum),payNum )) => (id, clickNum, orderNum.getOrElse(0) , payNum.getOrElse(0) )
    }

    val top10 = totalRdd.sortBy(x=> (x._2,x._3,x._4) ,false ).take(10)
    //10、结果展示
    top10.foreach(println)

    Thread.sleep(1000000)
  }
}
