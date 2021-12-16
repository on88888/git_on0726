package test.c2

/**
 * @Author 0726
 * @ClassName TestList
 * @createTime 2021年10月25日 14:24
 */
object TestList {
  def main(args: Array[String]): Unit = {

    //map 映射
    val list = List("hadoop","flume","spark","scala","akfka")
    val func =(x:String) =>x.length
    val list2 = list.map(func)
    println(list2)

    //flatten压平
    val list3: List[List[Int]] = List(List(1,2,3),List(4,5,6),List(6,7,8))
    val list4 = list3.flatten
    println(list4)

    val list5: List[String] = List("aaa","bbb","ccc")
    val list6 = list5.flatten
    println(list6)

    //flatMap= map + flatten
    val list8: List[String] = List("hello java spark","hello spark python","python spark scala")
    val list9: List[Array[String]] = list8.map(x=>x.split(" "))
    val list10: List[String] = list9.flatten
    println(list10)

    val list11: List[String] = list8.flatMap(x=>x.split(" "))
    println(list11)

    //foreach
    println("*"*10+"foreach"+"*"*10)
    list.foreach(x=>println(x))
    list.foreach(println(_))
    list.foreach(println)


    //filter  过滤
    val list12 = List(1,2,4,5,7,9)
    val list13 = list12.filter(x=> x%2==0)
    println(s"list13 : ${list13}")

    //groupBy
    val list14 = List(("zhangsan","man","shenzhen"),("lisi","woman","beijing"),("wangwu","woman","beijing"))
    val map: Map[String, List[(String, String, String)]] = list14.groupBy(x=>x._2)
    println(map)

    //reduce
    val list15 = List(1,3,5,7,9,11)
    val r: Int = list15.reduce((agg,curr)=>{
      println(s"reduce agg=${agg} curr=${curr}")
      agg+curr})
    println(r)

    //reduceRight
    list15.reduceRight((curr,agg)=>{
      println(s"reduce agg=${agg} curr=${curr}")
      agg+curr})

    //fold
    list15.fold(1000)((agg,curr)=>{
      println(s"fold agg=${agg} curr=${curr}")
      agg+curr})

    //foldRight
    list15.foldRight(1000)((curr,agg)=>{
      println(s"fold agg=${agg} curr=${curr}")
      agg+curr})

  }
}
