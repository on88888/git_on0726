package test.c2

import scala.collection.immutable.Queue
import scala.collection.mutable

/**
 * @Author 0726
 * @ClassName QueueTest
 * @createTime 2021年10月26日 09:01
 */
object QueueTest {

  def main(args: Array[String]): Unit = {

    val q1: Queue[Int] = Queue[Int](1,3,2,5,6,4,7,8,9)
    println(q1)

    println(q1.dequeue)

    val qq1 = mutable.Queue[Int](1,3,2,5,6)

    println(qq1)

    val qq2: mutable.Queue[Int] = qq1.+:(10)

    println(qq2)

  }

}
