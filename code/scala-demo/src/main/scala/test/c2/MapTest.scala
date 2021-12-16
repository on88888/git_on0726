package test.c2

import scala.collection.{immutable, mutable}

/**
 * @Author 0726
 * @ClassName MapTest
 * @createTime 2021年10月25日 08:59
 */
object MapTest {
  def main(args: Array[String]): Unit = {

    val map = Map[String,Int](("aa"->10),("bb"->20),("cc"->30))
    println(map)

    val map2 = map.+("dd"->40)
    println(map2)

    val map3 = map.++(List("ee"->50,("ff"->60)))
    println(map3)

    val map5 = map.-("aa")
    println(map5)

    println(map.getOrElse("a1a", -1))

    for(key<-map.keySet)
      println(key)

    for(value<-map.values)
      println(value)

    val map11: Any = map.+("aa"->100)
    println(map11)

  }

}
