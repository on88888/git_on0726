package test.c2

/**
 * @Author 0726
 * @ClassName CollectionTest
 * @createTime 2021年10月25日 10:23
 */
object CollectionTest {
  def main(args: Array[String]): Unit = {

    val list = List(1,3,7,9,10,2,5)

    val list2 = List( ("zhangsna",20,3),("lisi",30,4),("wangwu",25,1),("hanmeimei",50,10) ,("hanmeimei",18,10))

    println(list2.maxBy((x: (String, Int, Int)) => x._2))
    println(list2.maxBy(_._2))

    println(list.sorted)
    println(list.sorted.reverse)

    val order = new Ordering[Int]{
      override def compare(x: Int, y: Int) = {
        if(x<y) 1
        else if(x==y) 0
        else -1
      }
    }

    println(list2.sortBy(x => x._2)(order))

    println(list.sortWith((x: Int, y: Int) => x > y))
    println(list.sortWith((x: Int, y: Int) => x < y))

  }
}
