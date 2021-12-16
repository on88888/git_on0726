package test.c4

/**
 * @Author 0726
 * @ClassName Test4
 * @createTime 2021年10月29日 18:26
 */
object Test4 {
  def main(args: Array[String]): Unit = {
    val arr = Iterable( ("宝安区",15),("宝安区",10),("福田区",3),("福田区",10))
    val map: Map[String, Iterable[(String, Int)]] = arr.groupBy(_._1)
    //println(map)

    map.map(x=>{
      val totalTime = x._2.map(x=>x._2).sum
      val count = x._2.size
      (x._1,totalTime.toDouble/count)
    })
      .foreach(println)

    val add = List(("宝安区",15),("宝安区",10),("福田区",3),("福田区",10))
    println(add.map(_._2))
    println(add.map(_._2).sum)

    println(add.map(x=>{
      val a = x._2
      ("aaa",a)
    }))


  }
}
