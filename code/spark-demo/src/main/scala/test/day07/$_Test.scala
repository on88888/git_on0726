package test.day07

import org.junit.Test

/**
 * @Author 0726
 * @ClassName $_Test
 * @createTime 2021年11月05日 19:02
 */
class $_Test {

  @Test
  def get(): Unit ={

    val map = Map("lisi"->20,"zhangsan"->12,"li"-> "")
    println(map.getOrElse("zhangsan", -1))
  }
}
