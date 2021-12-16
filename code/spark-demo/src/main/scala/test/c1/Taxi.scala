package test.c1

import java.text.SimpleDateFormat

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author 0726
 * @ClassName Taxi
 * @createTime 2021年10月29日 15:07
 */
object Taxi {
  /**
    * 每个区域平均等客时间
    */
  def main(args: Array[String]): Unit = {

    //创建SparkContext
    //.setMaster("local[4]")在idea本地执行必须设置,提交到集群的时候不需要设置
    val conf = new SparkConf().setMaster("local[4]").setAppName("test")
//    val conf = new SparkConf().setAppName("test")  //集群
    val sc = new SparkContext(conf)

    //1、读取数据
    val rdd1: RDD[String] = sc.textFile("datas/taxi.txt")
//    val rdd1: RDD[String] = sc.textFile(args(0)) //集群

    //2、列裁剪[...] + 时间处理
    val rdd2: RDD[(String, String, String, Long, Long)] = rdd1.map(line=>{
      val arr = line.split("\t")
      val id = arr.head
      val fromAddr = arr(1)
      val toAddr = arr(2)
      val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val fromTime = formatter.parse(arr(3)).getTime
      val toTime = formatter.parse(arr(4)).getTime
      (id,fromAddr,toAddr,fromTime,toTime)
    })
      //rdd2.collect().foreach(println)
    //RDD(
    //    (A,龙华区,宝安区,2020-07-15 10:05:10,2020-07-15 10:25:02),
    //    (B,宝安区,福田区,2020-07-15 11:43:22,2020-07-15 11:55:45),
    //    (A,龙岗区,宝安区,2020-07-15 11:55:55,2020-07-15 12:12:23),
    //    (B,福田区,宝安区,2020-07-15 12:05:05,2020-07-15 12:22:33),
    //    (A,龙岗区,龙华区,2020-07-15 11:02:08,2020-07-15 11:17:15),
    //    (A,宝安区,龙岗区,2020-07-15 10:35:15,2020-07-15 10:40:50),
    //    (B,龙华区,龙岗区,2020-07-15 10:45:25,2020-07-15 10:50:00),
    //    (A,龙华区,龙岗区,2020-07-15 11:33:12,2020-07-15 11:45:35),
    //    (B,宝安区,龙岗区,2020-07-15 12:27:20,2020-07-15 12:43:31),
    //    (A,宝安区,龙岗区,2020-07-15 12:17:10,2020-07-15 12:33:21),
    //    (B,福田区,龙华区,2020-07-15 10:15:21,2020-07-15 10:35:12),
    //    (B,龙岗区,宝安区,2020-07-15 11:12:18,2020-07-15 11:27:25)
    // )

    //3、按照司机分组
    val rdd3: RDD[(String, Iterable[(String, String, String, Long, Long)])] = rdd2.groupBy(x=> x._1)
    //RDD(
    //    A -> List(
    //          (A,龙华区,宝安区,2020-07-15 10:05:10,2020-07-15 10:25:02),
    //          (A,龙岗区,宝安区,2020-07-15 11:55:55,2020-07-15 12:12:23),
    //          (A,龙岗区,龙华区,2020-07-15 11:02:08,2020-07-15 11:17:15),
    //          (A,宝安区,龙岗区,2020-07-15 10:35:15,2020-07-15 10:40:50),
    //          (A,龙华区,龙岗区,2020-07-15 11:33:12,2020-07-15 11:45:35),
    //          (A,宝安区,龙岗区,2020-07-15 12:17:10,2020-07-15 12:33:21),
    //      )
    //    B -> List(
    //          (B,宝安区,福田区,2020-07-15 11:43:22,2020-07-15 11:55:45),
    //          (B,福田区,宝安区,2020-07-15 12:05:05,2020-07-15 12:22:33),
    //          (B,龙华区,龙岗区,2020-07-15 10:45:25,2020-07-15 10:50:00),
    //          (B,宝安区,龙岗区,2020-07-15 12:27:20,2020-07-15 12:43:31),
    //          (B,福田区,龙华区,2020-07-15 10:15:21,2020-07-15 10:35:12),
    //          (B,龙岗区,宝安区,2020-07-15 11:12:18,2020-07-15 11:27:25)
    //  )
    // )

//    rdd3.collect().foreach(println)

    //4、对每个司机所有数据排序
    val rdd4: RDD[(String, Long)] = rdd3.flatMap(x=>{
      //x =     A -> List(
      //             (A,龙华区,宝安区,2020-07-15 10:05:10,2020-07-15 10:25:02),
      //             (A,宝安区,龙岗区,2020-07-15 10:35:15,2020-07-15 10:40:50),
      //             (A,龙岗区,龙华区,2020-07-15 11:02:08,2020-07-15 11:17:15),
      //             (A,龙华区,龙岗区,2020-07-15 11:33:12,2020-07-15 11:45:35),
      //             (A,龙岗区,宝安区,2020-07-15 11:55:55,2020-07-15 12:12:23),
      //             (A,宝安区,龙岗区,2020-07-15 12:17:10,2020-07-15 12:33:21),
      //         )
      val sortedList = x._2.toList.sortBy(y=> y._4)
      //List(
      //             (A,龙华区,宝安区,2020-07-15 10:05:10,2020-07-15 10:25:02),
      //             (A,宝安区,龙岗区,2020-07-15 10:35:15,2020-07-15 10:40:50),
      //             (A,龙岗区,龙华区,2020-07-15 11:02:08,2020-07-15 11:17:15),
      //             (A,龙华区,龙岗区,2020-07-15 11:33:12,2020-07-15 11:45:35),
      //             (A,龙岗区,宝安区,2020-07-15 11:55:55,2020-07-15 12:12:23),
      //             (A,宝安区,龙岗区,2020-07-15 12:17:10,2020-07-15 12:33:21),
      //         )
      //5、滑窗计算得到等客区域和等客时间
      val slidingList = sortedList.sliding(2)
      //List(
      //    List( (A,龙华区,宝安区,2020-07-15 10:05:10,2020-07-15 10:25:02), (A,宝安区,龙岗区,2020-07-15 10:35:15,2020-07-15 10:40:50) )
      //    List( (A,宝安区,龙岗区,2020-07-15 10:35:15,2020-07-15 10:40:50), (A,龙岗区,龙华区,2020-07-15 11:02:08,2020-07-15 11:17:15) )
      //    .....
      // )
      val regionList = slidingList.map(y=>{
        // y = List( (A,龙华区,宝安区,2020-07-15 10:05:10,2020-07-15 10:25:02), (A,宝安区,龙岗区,2020-07-15 10:35:15,2020-07-15 10:40:50) )
        val first = y.head
        val next = y.last
        val region = first._3
        val duration = (next._4 - first._5)/1000
        (region,duration)
      })

      regionList

    })

//    rdd4.collect().foreach(println)

    //RDD( (宝安区,15),(宝安区,10),(龙岗区,20),(福田区,30),(龙岗区,22),... )
    //6、按照等客区域分组
    val rdd5: RDD[(String, Iterable[(String, Long)])] = rdd4.groupBy(_._1)

//    rdd5.collect().foreach(println)

    //RDD(
    //    宝安区 -> Iterable( (宝安区,15),(宝安区,10),....)
    //     龙岗区-> Iterable( .... )
    // )
    //7、计算每个区域的平均等客时间
    val rdd6 = rdd5.map( x=>{
        //x = 宝安区 -> Iterable( (宝安区,15),(宝安区,10),....)
        //获取区域等客总时间
        val totalTime = x._2.map( _._2 ).sum
       //获取区域等客总次数
        val count = x._2.size
      (x._1, totalTime.toDouble / count)
    } )

    //8、结果展示
    val result = rdd6.collect()

    result.foreach(println)
  }
}
