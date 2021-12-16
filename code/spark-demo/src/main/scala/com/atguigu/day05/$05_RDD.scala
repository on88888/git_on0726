package com.atguigu.day05

object $05_RDD {
  /**
    *  依赖: 父子RDD之间的关系
    *     RDD的依赖分为两种: 宽依赖、窄依赖
    *         宽依赖: 有shuffle称之为宽依赖【 如果父RDD一个分区的数据被子RDD多个分区所使用的 】
    *         窄依赖: 没有shuffle称之为窄依赖 【 父RDD一个分区的数据只被子RDD一个分区所使用的 】
    *
    *  stage切分: 根据最后一个rdd的依赖关系向前找父RDD，然后根据父RDD的依赖关系继承向前,依次查找,一直找到第一个RDD位置。在中间查询的过程中如果父子依赖关系是宽依赖则切分stage.
    *  stage的执行: 一个job中的stage是从前往后执行,先执行第一个stage，在执行后面的stage
    *  Application: 应用, 一个sparkcontext称之为一个应用。
    *       job: 任务, 一般一个action算子产生一个Job [first与take除外]
    *           stage: 阶段, job中stage的个数 = shuffle个数+1
    *               task: 子任务, 一个stage中task的个数 = stage中最后一个rdd的分区数
    *  一个job中多个stage的执行是串行
    *  一个stage中多个task的执行是并行
    *
    */
  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    val rdd1 = sc.textFile("datas/wc.txt",4)
    println("+"*100)
    println(rdd1.dependencies)
    println("-"*100)
    val rdd2 = rdd1.flatMap(_.split(" "))
    println(rdd2.dependencies)
    println("-"*100)
    val rdd3 = rdd2.map((_,1))
    println(rdd3.dependencies)
    val rdd31 = rdd3.coalesce(3)
    println("-"*100)
    val rdd4 = rdd31.reduceByKey(_+_)
    println(rdd4.dependencies)
    println("+"*100)
    println(rdd4.collect().toList)

    Thread.sleep(1000000)
  }
}
