package test.c2

/**
 * @Author 0726
 * @ClassName WordCountHighTest
 * @createTime 2021年10月25日 16:43
 */
object WordCountHighTest {
  def main(args: Array[String]): Unit = {

    val tupleList = List(("Hello Scala Spark World", 4), ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))

    //1、切割[需要初始化单词的次数] + 压平
    val splitList = tupleList.flatMap(x=>{
//      val arr = x._1.split(" ")
//      arr.map(y=>(y,x._2))
      x._1.split(" ").map(y=>(y,x._2))
    })
    println(splitList)

    //2、按照单词分组
    val groupMap: Map[String, List[(String, Int)]] = splitList.groupBy(x=>x._1)
    println(groupMap)

    //3、统计每个单词的总次数
    val result = groupMap.map(x=>{
      val count = x._2.map(y=>y._2).sum
      (x._1,count)
    })

    result.foreach(println)

  }
}
