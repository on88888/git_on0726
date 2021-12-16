package test.c1

/**
 * @Author 0726
 * @ClassName TT
 * @createTime 2021年10月21日 14:31
 */
object TT {
  def main(args: Array[String]): Unit = {
    val b = for (i <- 1 to 3) yield{
      if (i != 2) {
       i
      }
    }
    println(b.toList)

    val a = for (i <- 1 to 10 if i != 5) yield{
      i
    }
    println(a)


  }

}
