package test.c1

/**
 * @Author 0726
 * @ClassName Test
 * @createTime 2021年10月29日 11:37
 */
object Test {
  def main(args: Array[String]): Unit = {

    val a = "hello"
    println(a.hashCode%3)

    println("spark".hashCode%3)

  }
}
