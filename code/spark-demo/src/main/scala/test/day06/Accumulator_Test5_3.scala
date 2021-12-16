package test.day06

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
 * @Author 0726
 * @ClassName Accumulator_Test5_3
 * @createTime 2021年11月04日 11:15
 */
class Accumulator_Test5_3 extends AccumulatorV2[(String,(Int,Int,Int)),mutable.Map[String,(Int,Int,Int)]]{
  //创建中间容器
  private val tmp = mutable.Map[String,(Int,Int,Int)]()
  //判断累加器是否为空
  override def isZero: Boolean = tmp.isEmpty
  //复制累加器
  override def copy(): AccumulatorV2[(String, (Int, Int, Int)), mutable.Map[String, (Int, Int, Int)]] = new Accumulator_Test5_3
  //重置累加器
  override def reset(): Unit = tmp.clear()

  /**
   * 在每个分区中累加每个品类的次数
   * @param v
   */
  override def add(v: (String, (Int, Int, Int))): Unit = {
    //获取之前的统计次数
    val currCount = tmp.getOrElse(v._1,(0,0,0))
    //将当前key的次数与之前的次数相加
    val aggCount = (v._2._1+currCount._1,v._2._2+currCount._2,v._2._3+currCount._3)
    //将累加结果放入容器
    tmp.put(v._1,aggCount)
  }

  /**
   * 在driver合并task的累加结果
   * @param other
   */
  override def merge(other: AccumulatorV2[(String, (Int, Int, Int)), mutable.Map[String, (Int, Int, Int)]]): Unit = {
    //取出task的累加结果
    val taskMap = other.value

    taskMap.foreach{
      case(id,(djCs,xdCs,zfCs))=>
        //获取之前的统计次数
        val currCount = tmp.getOrElse(id,(0,0,0))
        //将当前key的次数与之前的次数相加
        val aggCount = (djCs+currCount._1,xdCs+currCount._2,zfCs+currCount._3)
        //将累加结果放入容器
      tmp.put(id,aggCount)
    }
    //获取之前的统计次数
    //将当前key的次数与之前的次数相加

  }

  /**
   * 获取最终结果
   * @return
   */
  override def value: mutable.Map[String, (Int, Int, Int)] = tmp
}
