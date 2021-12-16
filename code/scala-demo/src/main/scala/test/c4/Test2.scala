package test.c4

import java.text.SimpleDateFormat

import scala.io.Source

/**
 * @Author 0726
 * @ClassName Test2
 * @createTime 2021年10月26日 20:38
 */
object Test2 {

  //需求: 统计每个区域的平均等待时间

  def main(args: Array[String]): Unit = {

    //读取数据
    val driverInfo = Source.fromFile("datas/taxi.txt").getLines().toList
    //切割, 转换时间字符串为时间戳
    val result = driverInfo.map(line => {
      val arr = line.split("\t")
      val id = arr.head
      val toAddr = arr(2)
      val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val fromTime = formatter.parse(arr(3)).getTime
      val toTime = formatter.parse(arr.last).getTime

      (id, toAddr, fromTime, toTime)
    })
      //按照司机进行分组, 对每个司机的数据按照时间排序
      .groupBy(x =>
        x match {
          case (id, toAddr, fromTime, toTime) => id
        }
      )
      .toList
      .flatMap(x => {

        val sortedList = x._2.sortBy(_._3)
        val slidingList = sortedList.sliding(2)
//      slidingList: 滑窗->2
//        List((A,宝安区,1594778710000,1594779902000), (A,龙岗区,1594780515000,1594780850000))
//        List((A,龙岗区,1594780515000,1594780850000), (A,龙华区,1594782128000,1594783035000))
//        List((A,龙华区,1594782128000,1594783035000), (A,龙岗区,1594783992000,1594784735000))
//        List((A,龙岗区,1594783992000,1594784735000), (A,宝安区,1594785355000,1594786343000))  ......

        //List(List((a,龙岗区,11,20),(a,宝安区,22,26) )  ,List( (a,宝安区,22,26)，(a,龙华区,30,40)))
        val durationList = slidingList.map(z => {
          val first = z.head
//          println(z.head) <==> println(z(0))
          val last = z.last
          val region = z(0)._2
//          println(z(0))
//          println(z(1))
//          println(z)
          val duration = (last._3 - first._4) / 1000
          (region, duration)
        })

        durationList
      } )

      //按照区域分组, 统计平均等客时间
      .groupBy(_._1)
      .map(x => {
        val timeSum = x._2.map(_._2).sum
        val count = x._2.size
        (x._1, timeSum.toDouble / count)
      })

      //结果展示
      .foreach(println(_))


  }

}
