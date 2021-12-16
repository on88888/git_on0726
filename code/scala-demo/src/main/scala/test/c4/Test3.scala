package test.c4

/**
 * @Author 0726
 * @ClassName Test3
 * @createTime 2021年10月28日 13:41
 */
object Test3 {
  def main(args: Array[String]): Unit = {

      val arr = Array[String]("man shenzhen","woman beijing","woman shenzhen")
      arr.flatMap(x=>x.split(" "))
      .groupBy(x=>x)
      .map(x=>(x._1,x._2.size))
      .foreach(println)

/*    val array: Array[String] = arr.flatMap(x=>x.split(" "))
    println(array)
    println(array.toList)*/
  }
}
