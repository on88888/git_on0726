package test.day07

import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName $Test2
 * @createTime 2021年11月05日 19:20
 */
object $Test2 {

  def main(args: Array[String]): Unit = {
    
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)
    
    val rdd1 = sc.textFile("datas/user_visit_action.txt")
    
    val rdd2 = rdd1.filter(_.split("_")(5)=="null")

    val rdd3 = rdd2.flatMap(x=>{
      val arr = x.split("_")
      val djid = arr(6)
      val xdid = arr(8)
      val zfid = arr(10)
      if (djid!="-1"){
        (djid,(1,0,0))::Nil
      }else if(xdid!="null"){
        xdid.split(",").map(id=>(id,(0,1,0)))
      }else{
        zfid.split(",").map(id=>(id,(0,0,1)))
      }
    })

    //创建累加器对象
    val acc = new top10Acc
    //注册
    sc.register(acc,"top10_2")
    rdd3.foreach(x=>acc.add(x))

    val totalMap = acc.value
//    val top10 = totalMap.toList.sortBy(x=>x._2).reverse.take(10)
//    top10.foreach(println)
    val top10id = totalMap.toList.sortBy(x=>x._2).reverse.take(10).map(_._1)

    val rdd11 = rdd2.filter(line=>{
      val arr = line.split("_")
      val id = arr(6)
      id!="-1"&&top10id.contains(id)
    })

    val rdd12 = rdd11.map(line=>{
      val arr = line.split("_")
      val session = arr(2)
      val id = arr(6)
      ((id,session),1)
    })
    //((agg,curr)=>agg+curr)<==>(_+_)
    val rdd13 = rdd12.reduceByKey((agg,curr)=>agg+curr)

    val rdd14 = rdd13.groupBy{
      case ((id,session),num) => id
    }

    val rdd15 = rdd14.map(x=>{
    val top10session = x._2.toList.sortBy{
      case ((id,session),num) => num
    }.reverse
      .take(10)
      .map{
        case ((id,session),num)=>(session,num)
      }
      (x._1,top10session)
    })

    rdd15.collect().foreach(println)



  }

}
