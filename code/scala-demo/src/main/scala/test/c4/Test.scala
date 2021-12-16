package test.c4

import java.text.SimpleDateFormat

import scala.io.Source

/**
 * @Author 0726
 * @ClassName Test
 * @createTime 2021年10月26日 19:36
 */
object Test {
  def main(args: Array[String]): Unit = {

    //读取数据
    val data = Source.fromFile("datas/taxi.txt").getLines().toList

    data.map(line => {
      val arr = line.split("\t")
      val id = arr.head
      val toAddr = arr(2)
      val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val fromTime = formatter.parse(arr(3)).getTime
      val toTime = formatter.parse(arr.last).getTime

      (id, toAddr, fromTime, toTime)
    })
      .groupBy(x =>
        x match {
          case (id, toAddr, fromTime, toTime) => id
        }
      )
      .toList
      .foreach(println)





  }
}
