package com.atguigu.day07.$123_case

/**
 * @Author 0726
 * @ClassName $02_Test
 * @createTime 2021年11月05日 15:14
 */
object $02_Test {

  /**
    * top10热门品类中每个品类的top10活跃session
    *
    */
  def main(args: Array[String]): Unit = {


    //1、获取top10热门品类
    //1.1、创建sparkcontext
    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    //1.2、读取数据
    val rdd1 = sc.textFile("datas/user_visit_action.txt")

    //1.3、过滤[过滤掉搜索行为数据]
    val rdd2 = rdd1.filter(line=>{
      val arr = line.split("_")
      arr(5)=="null"
    })

    //1.4、切割、炸开、数据转换
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

    //1.4 创建累加器对象
    val acc = new Top10Accumulator

    //1.5 注册
    sc.register(acc,"top10")

    rdd3.foreach(x=> acc.add(x))

    //1.6 得到统计结果
    val totalMap = acc.value

    //1.7 排序取前十
    val top10ids= totalMap.toList.sortBy(x=>x._2).reverse.take(10).map(_._1)


    //2、过滤[只要top10品类的数据、只要点击数据]
    val rdd11 = rdd2.filter(line=> {
      val arr = line.split("_")
      val id = arr(6)
      id!="-1" && top10ids.contains(id)
    })
    //3、列裁剪[ 点击品类id, session ]
    val rdd12 = rdd11.map(line=>{
      val arr = line.split("_")
      val session = arr(2)
      val id = arr(6)

      ( (id,session),1 )
    })
    //4、统计每个品类每个session点击次数
    //数据量大使用reduceByKey
    //数据量小使用累加器
    val rdd13 = rdd12.reduceByKey(_+_)
    //5、按照品类分组
    val rdd14 = rdd13.groupBy{
      case ( (id,session),num ) => id
    }
    //6、对每个品类的所有session按照点击次数排序,取前十
  val rdd15 = rdd14.map(x=>{
    //x = (15,List( ((15,S1),10 ),((15,S4),3 ),((15,S2),6 ),....)
    val top10session = x._2.toList.sortBy{
      case  ( (id,session),num ) => num
    }.reverse
      .take(10)
      .map{
      case  ( (id,session),num ) => (session,num)
    }

    (x._1, top10session)
  })
    //7、结果展示
    rdd15.collect().foreach(println)

  }
}
