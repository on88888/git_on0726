package test.c2

/**
 * @Author 0726
 * @ClassName ListTest
 * @createTime 2021年10月25日 10:02
 */
object ListTest {

  def main(args: Array[String]): Unit = {

    val list = List(1,3,5,7,9,1,5,7,9)
    println(list)

    val list2: List[Int] = list.distinct
    println(list2)

    println(list.drop(2))

    println(list.dropRight(3))

    println(list2.reverse)

    println(list.sliding(4,2).toList)
    println(list.take(3))
    println(list.takeRight(3))
  }
}
