package com.atguigu.day06

object $04_BroadCast {

  /**
    * 广播变量
    *     原因:  spark算子里面的代码是在executor的task执行的,spark算子外面的代码是在driver执行的。如果spark算子里面有使用driver的数据,此时driver会将该数据向所有的task都发送一份，会占用过多的内存空间
    *     场景:
    *         1、spark算子中使用了driver的数据的时候,并且该数据的大小还有点大,此时可以将数据广播出去减少数据的内存占用
    *         2、大表 join 小表的时候,将小表广播出去减少shuffle操作
    *     使用:
    *           1、广播数据:  val bc = sc.broadcast(数据)
    *           2、使用广播数据: bc.value
    */

  /**
    * 1、spark算子中使用了driver的数据的时候,并且该数据的大小还有点大,此时可以将数据广播出去减少数据的内存占用
    * @param args
    */
  def main1(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    val rdd = sc.parallelize(List("jd","pdd","tm","atguigu"))
    //
    val map = Map[String,String]("jd"->"www.jd.com","pdd"->"www.pdd.com","tm"->"www.tm.com","atguigu"->"www.atguigu.com")

    //广播数据
    val bc = sc.broadcast(map)

    val rdd2 = rdd.map(x=> {
      val m = bc.value
      m.getOrElse(x,"")
      //map.getOrElse(x,"")
    })

    println(rdd2.collect().toList)

    Thread.sleep(1000000)
  }

  /**
    * 2、大表 join 小表的时候,将小表广播出去减少shuffle操作
    *
    */
  def main(args: Array[String]): Unit = {

     import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    //获取没有农贸市场的省份
    //读取数据
    val allprovincerdd = sc.textFile("datas/allprovince.txt")  //所有省份

    val productRdd = sc.textFile("datas/product.txt")

    //过滤
    val productFilterRdd = productRdd.filter(line=> line.split("\t").length==6)
    //列裁剪
    val provinceRdd = productFilterRdd.map(line=>{
      val arr = line.split("\t")
      arr(4)
    })
    //去重
    val disProvinceRdd = provinceRdd.distinct()

    val provinceKVRDD = disProvinceRdd.map(x=>(x,""))

    val allProvinceKVRDD = allprovincerdd.map(x=>(x,""))

    //广播全国所有生成
    val provinceList = provinceKVRDD.collect
    val bc = sc.broadcast( provinceList )

    //val allRdd = allProvinceKVRDD.leftOuterJoin(provinceKVRDD)

    //val resRdd = allRdd.filter{
    //  case (province,( leftvalue,rightValue )) => rightValue.isEmpty
    //}

    val resRdd = allProvinceKVRDD.filter{
      case (province,_) =>
        ! bc.value.map(_._1).contains( province )
    }

    resRdd.foreach(println)

    Thread.sleep(10000000)

  }
}
