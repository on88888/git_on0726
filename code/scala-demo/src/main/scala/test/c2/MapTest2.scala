package test.c2

import scala.collection.mutable


/**
 * @Author 0726
 * @ClassName MapTest2
 * @createTime 2021年10月25日 09:24
 */
object MapTest2 {
  def main(args: Array[String]): Unit = {
    val map = mutable.Map[String,Int]("aa"->10,"bb"->20,"cc"->30)
    println(map)

    map.+=("ab"->11)
    println(map)

    val list = List(1,3,5,7,9)

    println(list.contains(90))
    println(list.isEmpty)

    println(list.size)
    println(list.length)
    //println(list.toString())
    println(list.mkString("|"))

    val iterator: Iterator[Int] = list.iterator
    while (iterator.hasNext){
      println(iterator.next())
    }

    println("*"*10)

    val iterable: Iterable[Int] = list.toIterable
    for(ele<-iterable){
      println(ele)
    }

  }
}
