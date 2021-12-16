package test.day07

/**
 * @Author 0726
 * @ClassName $Test3
 * @createTime 2021年11月05日 19:20
 */
object $Test3 {
  // 求 1->2,2->3,3->4,4->5,5->6,6->7的转化率
  //  1->2转化率 : 从1跳到2页面的人数/访问1页面的总人数
  def main(args: Array[String]): Unit = {

    //设置页面id
    val list = List(1,2,3,4,5,6,7)
    //设置固定跳转选项
    val pagList = list.init.zip(list.tail)

    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd1 = sc.textFile("datas/user_visit_action.txt")

    val rdd2 = rdd1.filter(_.split("_")(6)!="-1")
//    rdd2.collect().toList.foreach(println)
    val rdd3 = rdd2.map(line=>{
      val arr = line.split("_")
      val session = arr(2)
      val pageid = arr(3).toInt
      val time = arr(4)

      (session,pageid,time)
    })

    val fmFilterRdd = rdd3.filter{
      case (session,pageid,time) => list.contains(pageid)
    }
//    fmFilterRdd.collect().toList.foreach(println)

    //(页面id,1)
    val fmSelectRdd = fmFilterRdd.map{
      case (session,pageid,time) =>(pageid,1)
    }
    //每个页面的访问次数
    val fmRdd = fmSelectRdd.reduceByKey(_+_)
    
    val fm = fmRdd.collect().toMap
//    fm.foreach(println)

    val fzGroupRdd = rdd3.groupBy{
      case (session,pageid,time) => session
    }
//    fzGroupRdd.collect().toList.foreach(println)

    val fzMapRdd = fzGroupRdd.flatMap(x=>{
      //6.2、对每个session所有数据按照时间排序
      val sortedList = x._2.toList.sortBy(_._3)
      //6.3、滑窗[得到上下两个页面,从而得到从哪个页面跳入哪个页面]
      val slidingList = sortedList.sliding(2)
      val fromToPageList = slidingList.map(y=>{
        val fromPage = y.head._2
        val toPage = y.last._2
        ((fromPage,toPage),1)
      })
      //6.4、过滤[只要1->2,2->3,3->4,4->5,5->6,6->7的跳转数据]
      val r = fromToPageList.filter{
        case ((fromPage,toPage),_) => pagList.contains((fromPage,toPage))
      }

      r
    })

    val fzRdd = fzMapRdd.reduceByKey(_+_)
    
//    fzRdd.collect().foreach(println)
    val fz = fzRdd.collect().toMap
    
    pagList.foreach{
      case (fromPage,toPage) =>
        //从分子中取出frompage跳toPage的总次数
        val fzNum = fz.getOrElse((fromPage, toPage), 0)
        //从分母中取出fromPage的访问总次数
        val fmNum = fm.getOrElse(fromPage,1)

        println(s"从${fromPage}到${toPage}的转化率=${(fzNum.toDouble/fmNum) *100}%")
    }

  }
}
