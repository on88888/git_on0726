package test.c2

import scala.collection.mutable.ArrayBuffer

/**
 * @Author 0726
 * @ClassName Test121
 * @createTime 2021年10月23日 14:35
 */
object Test121 {
  def main(args: Array[String]): Unit = {

    //（1）创建并赋值可变数组
    val arr01 = ArrayBuffer[Any](1, 2, 3)

    //（2）遍历数组
    for (i <- arr01) {
      println(i)
    }
    println(arr01.length) // 3
    println("arr01.hash=" + arr01.hashCode())

    //（3）增加元素
    //（3.1）追加数据
    arr01.+=(4)
    //（3.2）向数组最后追加数据
    arr01.append(5,6)
    //（3.3）向指定的位置插入数据
    arr01.insert(0,7,8)
    println("arr01.hash=" + arr01.hashCode())

    //（4）修改元素
    arr01(1) = 9 //修改第2个元素的值
    println("--------------------------")

    for (i <- arr01) {
      println(i)
    }
    println(arr01.length) // 5
  }
}
