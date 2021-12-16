package com.atguigu.day07.$123_case

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class Top10Accumulator extends AccumulatorV2[ (String, (Int,Int,Int) ) , mutable.Map[String,(Int,Int,Int)]]{

  val tmpMap = mutable.Map[String,(Int,Int,Int)]()

  /**
    * 累加器是否为空
    * @return
    */
  override def isZero: Boolean = tmpMap.isEmpty

  /**
    * 复制累加器
    * @return
    */
  override def copy(): AccumulatorV2[(String, (Int, Int, Int)), mutable.Map[String, (Int, Int, Int)]] = new Top10Accumulator

  /**
    * 重置累加器
    */
  override def reset(): Unit = tmpMap.clear

  /**
    * 在每个分区中累加每个品类的次数
    * @param v
    */
  override def add(v: (String, (Int, Int, Int))): Unit = {
    //获取之前的统计次数
    val beforeNum = tmpMap.getOrElse(v._1, (0,0,0) )
    //将当前key的次数与之前的次数相加
    val totalNum = (beforeNum._1 + v._2._1 ,beforeNum._2+v._2._2 , beforeNum._3+v._2._3)
    //将累加结果放入容器
    tmpMap.put(v._1,totalNum )
  }

  /**
    * 在driver合并task的累加结果
    * @param other
    */
  override def merge(other: AccumulatorV2[(String, (Int, Int, Int)), mutable.Map[String, (Int, Int, Int)]]): Unit = {

    //第一种
    //取出task的累加结果
    val taskMap = other.value
    taskMap.foreach{
      case (id,(clickNum,orderNum,payNum)) =>
        //获取之前的统计次数
        val beforeNum = tmpMap.getOrElse(id, (0,0,0) )
//        println(s"${Thread.currentThread()getName} 之前--${tmpMap}  --${beforeNum}")
        //将当前key的次数与之前的次数相加
        val totalNum = (beforeNum._1 + clickNum ,beforeNum._2+orderNum , beforeNum._3+payNum)
//        println(s"${Thread.currentThread()getName} 现在--${totalNum}")
        //将累加结果放入容器
        tmpMap.put(id,totalNum )
//        println(s"${Thread.currentThread()getName} 结果--${tmpMap} ")
    }

    //第二种
/*    val totalList = other.value.toList ++ tmpMap.toList

    val groupedMap = totalList.groupBy(_._1)
    //Map(
    //    A -> List( (A,(10,3,2)) ,(A,(5,2,1)))
    // )
    val total = groupedMap.map(x=>{
      //x = A -> List( (A,(10,3,2)) ,(A,(5,2,1)))
      val totalNum = x._2.map(_._2)   // Map((10,3,2),(5,2,1),...)

      val num = totalNum.reduce( (agg,curr)=>(agg._1+curr._1, agg._2+curr._2 , agg._3+curr._3) )
      (x._1, num)
    })

    tmpMap.++=(total)*/
  }

  /**
    * 获取最终结果
    * @return
    */
  override def value: mutable.Map[String, (Int, Int, Int)] = tmpMap
}
