package com.atguigu.day07.$123_case

/**
 * @Author 0726
 * @ClassName $03_Test
 * @createTime 2021年11月05日 15:14
 */
object $03_Test {

  /**
    * 求单跳转化率
    */
  def main(args: Array[String]): Unit = {

    val list = List(1,2,3,4,5,6,7)
    
    val pageList = list.init.zip(list.tail)
    println(pageList)
    //求 1->2,2->3,3->4,4->5,5->6,6->7的转化率
    //  1->2转化率 : 从1跳到2页面的人数/访问1页面的总人数

    //1、创建sparkcontext
    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    //2、读取数据
    val rdd1 = sc.textFile("datas/user_visit_action.txt")

    //3、过滤[只需要点击数据]
    val rdd2 = rdd1.filter(line=>{
      line.split("_")(6)!="-1"
    })
    //4、列裁剪 [ sessionid, 页面id,时间 ]
    val rdd3 = rdd2.map(line=>{
      val arr = line.split("_")
      val session = arr(2)
      val pageid = arr(3).toInt
      val time = arr(4)

      (session,pageid,time)
    })
    //5、获取每个页面的访问次数[分母]
    //5.1、过滤掉除开1,2,3,4,5,6,7页面的数据
    val fmFilterRdd = rdd3.filter{
      case  (session,pageid,time) => list.contains(pageid)
    }
//    println(fmFilterRdd.collect().toList)
    //5.2、列裁剪[ 页面id ]
    val fmSelectRdd = fmFilterRdd.map{
      case (session,pageid,time) => (pageid,1)
    }
    //5.3、统计每个页面的访问总次数
    val fmRdd = fmSelectRdd.reduceByKey(_+_)

    val fm = fmRdd.collect().toMap
    //RDD(1->100,2->80,3->90,...)
    //6、获取每个跳转的次数
    //6.1、按照session分组
    val fzGroupedRdd = rdd3.groupBy{
      case (session,pageid,time) => session
    }
    //RDD(
    //    S1 -> Iterable( (S1,P1,1),(S1,P5,6),(S1,P3,4),(S1,P2,2),(S1,P7,3),(S1,P8,5)... )
    //    .....
    // )
    //6.2、对每个session所有数据按照时间排序
    val fzMapRdd = fzGroupedRdd.flatMap( x=>{
      //x = S1 -> Iterable( (S1,P1,1),(S1,P5,6),(S1,P3,4),(S1,P2,2),(S1,P7,3),(S1,P8,5)... )
      val sortedList = x._2.toList.sortBy(_._3)
      //List( (S1,P1,1),(S1,P2,2),(S1,P7,3),(S1,P3,4), (S1,P8,5),(S1,P5,6),...)
      //6.3、滑窗[得到上下两个页面,从而得到从哪个页面跳入哪个页面]
      val slidingList = sortedList.sliding(2)
      //Iterator( List( (S1,P1,1),(S1,P2,2) ) ,List( (S1,P2,2),(S1,P7,3) ) ,List( (S1,P7,3),(S1,P3,4) ),... )
      val fromToPageList = slidingList.map(y=>{
        //y = List( (S1,P1,1),(S1,P2,2) )
        val fromPage = y.head._2
        val toPage = y.last._2
        ((fromPage,toPage),1)
      })
      //6.4、过滤[只要1->2,2->3,3->4,4->5,5->6,6->7的跳转数据]
      val r = fromToPageList.filter{
        case ( ((fromPage,toPage),_) ) => pageList.contains( (fromPage,toPage) )
      }

      r
    } )

    //6.5、统计每个跳转的总次数
    val fzRdd = fzMapRdd.reduceByKey(_+_)
    //RDD( (1,2) ->10, (2,3)->40 )
    val fz = fzRdd.collect().toMap
    //7、求得转化率
    pageList.foreach{
      case (fromPage,toPage) =>
        //从分子中取出frompage跳toPage的总次数
        val fzNum = fz.getOrElse((fromPage, toPage), 0)

        //从分母中取出fromPage的访问总次数
        val fmNum = fm.getOrElse(fromPage,1)

        println(s"从${fromPage}到${toPage}的转化率=${(fzNum.toDouble/fmNum) *100}%")

    }

  }
}
