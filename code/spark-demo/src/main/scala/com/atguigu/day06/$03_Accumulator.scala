package com.atguigu.day06

object $03_Accumulator {
  /**
    * 累加器
    *     原因: 累加器可以在一定程度上减少shuffle操作
    *     场景: 用于聚合结果不是太大的场景
    *     原理：首先在每个分区中聚合,然后将每个分区的聚合结果发给Driver汇总
    */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )
    //创建自定义累加器对象
    val acc = new WordCountAccumulator
    //val acc = sc.longAccumulator("acc")
    //注册累加器
    sc.register(acc,"wcacc")
    //val rdd = sc.parallelize(List(10,20,30,40))
    val rdd  =sc.textFile("datas/wc.txt")

    val rdd2 = rdd.flatMap(_.split(" ")).map((_,1))

    //rdd2.reduce( (agg,curr)=> )

    //var sum = 0
    //val r = rdd.reduce((agg,curr)=> agg+curr)
    //rdd.foreach(x=>acc.add(x))

    //println(acc.value)
    //println(r)
    rdd2.foreach(x=> acc.add(x))

    println(acc.value)

    Thread.sleep(1000000)
  }
}

//add Executor task launch worker for task 0 -- 传入数据: (hello,1)  --之前计算的中间结果: Map()
//add Executor task launch worker for task 0 -- 传入数据: (spark,1)  --之前计算的中间结果: Map(hello -> 1)
//add Executor task launch worker for task 0 -- 传入数据: (hello,1)  --之前计算的中间结果: Map(spark -> 1, hello -> 1)
//add Executor task launch worker for task 0 -- 传入数据: (python,1)  --之前计算的中间结果: Map(spark -> 1, hello -> 2)
//add Executor task launch worker for task 0 -- 传入数据: (scala,1)  --之前计算的中间结果: Map(spark -> 1, python -> 1, hello -> 2)
//add Executor task launch worker for task 0 -- 传入数据: (spark,1)  --之前计算的中间结果: Map(spark -> 1, scala -> 1, hello -> 2, python -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (spark,1)  --之前计算的中间结果: Map()
//add Executor task launch worker for task 1 -- 传入数据: (kafka,1)  --之前计算的中间结果: Map(spark -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (flume,1)  --之前计算的中间结果: Map(spark -> 1, kafka -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (flume,1)  --之前计算的中间结果: Map(spark -> 1, flume -> 1, kafka -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (kafka,1)  --之前计算的中间结果: Map(spark -> 1, flume -> 2, kafka -> 1)
//add Executor task launch worker for task 1 -- 传入数据: (scala,1)  --之前计算的中间结果: Map(spark -> 1, flume -> 2, kafka -> 2)
//merge  dag-scheduler-event-loop -- 传入的一个task汇总数据数据: Map(spark -> 2, scala -> 1, python -> 1, hello -> 2)  --之前Driver的汇总结果: Map()
//merge  dag-scheduler-event-loop -- 传入的一个task汇总数据数据: Map(spark -> 1, scala -> 1, flume -> 2, kafka -> 2)  --之前Driver的汇总结果: Map(spark -> 2, scala -> 1, hello -> 2, python -> 1)
//Map(spark -> 3, scala -> 2, flume -> 2, kafka -> 2, hello -> 2, python -> 1)