package test.c2

import scala.io.Source

/**
 * @Author 0726
 * @ClassName WordCountTest
 * @createTime 2021年10月25日 16:28
 */
object WordCountTest {
  def main(args: Array[String]): Unit = {

    //1、读取数据
    val datas = Source.fromFile("datas/wc.txt").getLines().toList
    println(s"datas : ${datas}")

    //2、切割 map +压平 flatten
    val splitList: List[String] = datas.flatMap(x=>x.split(" "))
    println(s"splitList : ${splitList}")

    val splitList1: List[Char] = datas.flatMap(x=>x.split(" ")(2))
    println(splitList1)

    //3、按照单词分组
    val groupedMap: Map[String, List[String]] = splitList.groupBy(x=>x)
    println(s"groupedMap : ${groupedMap}")

    //4、统计次数
    val result = groupedMap.map(x=>{
      (x._1,x._2.size)
    })
    println(s"result : ${result}")

    //5、结果展示
    result.foreach(println)

    println("="*30)

    Source.fromFile("datas/wc.txt")
      .getLines()
      .toList
      .flatMap(x=>x.split(" "))
      .groupBy(x=>x)
      .map(x=>(x._1,x._2.length))
      .foreach(println)

  }
}
