package test.day07

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
 * @Author 0726
 * @ClassName top10Acc
 * @createTime 2021年11月05日 19:39
 */
class top10Acc extends AccumulatorV2[(String,(Int,Int,Int)),mutable.Map[String,(Int,Int,Int)]]{

  //创建中间容器
  private val tmpMap = mutable.Map[String,(Int,Int,Int)]()

  override def isZero = tmpMap.isEmpty

  override def copy() = new top10Acc

  override def reset(): Unit = tmpMap.clear()

  override def add(v: (String, (Int, Int, Int))): Unit = {
    val beforeNum = tmpMap.getOrElse(v._1,(0,0,0))
    val totalNum = (beforeNum._1+v._2._1,beforeNum._2+v._2._2,beforeNum._3+v._2._3)
    tmpMap.put(v._1,totalNum)
  }

  override def merge(other: AccumulatorV2[(String, (Int, Int, Int)), mutable.Map[String, (Int, Int, Int)]]): Unit = {
    val taskMap = other.value
    taskMap.foreach{
      case (id,(djid,xdid,zfid)) =>
        val beforeNum = tmpMap.getOrElse(id, (0, 0, 0))
        val totalNum = (beforeNum._1+djid,beforeNum._2+xdid,beforeNum._3+zfid)
        tmpMap.put(id,totalNum)
    }
  }

  override def value: mutable.Map[String, (Int, Int, Int)] = tmpMap
}
