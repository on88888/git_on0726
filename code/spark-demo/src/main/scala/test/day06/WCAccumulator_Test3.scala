package test.day06

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
 * @Author 0726
 * @ClassName WCAccumulator_Test3
 * @createTime 2021年11月03日 18:49
 */

/**
 * 自定义累加器
 */
class WCAccumulator_Test3 extends AccumulatorV2[(String,Int),mutable.Map[String,Int]]{

  //创建中间结果容器
  private val tmpMap: mutable.Map[String, Int] = mutable.Map[String,Int]()

  //判断累加器是否为空
  override def isZero: Boolean = tmpMap.isEmpty

  //复制累加器
  override def copy(): AccumulatorV2[(String, Int), mutable.Map[String, Int]] = new WCAccumulator_Test3

  //重置累加器
  override def reset(): Unit = tmpMap.clear()

  //累加每个分区的数据
  override def add(v: (String, Int)): Unit = {

    println(s"add ${Thread.currentThread().getName} --传入数据：${v}  --之前计算的中间值 ：${tmpMap}")

    val count = tmpMap.getOrElse(v._1,0) + v._2
    tmpMap.put(v._1,count)
  }

  //合并所有分区(task)的结果[在driver执行]
  override def merge(other: AccumulatorV2[(String, Int), mutable.Map[String, Int]]): Unit = {

    //获取task累加器结果
    val taskMap: mutable.Map[String, Int] = other.value
    //将task结果与driver之前的汇总合并
    val totalList = taskMap.toList ++ tmpMap.toList
    val groupMap: Map[String, List[(String, Int)]] = totalList.groupBy(x=>x._1)

    val result = groupMap.map(x=>{
      val sum: Int = x._2.map(x=>x._2).sum
      (x._1,sum)
    })

    //将本次结果放入临时变量容器中，便于下一次与下一个task 累加器汇总
    tmpMap.++=(result)
  }

  override def value: mutable.Map[String, Int] = tmpMap
}
