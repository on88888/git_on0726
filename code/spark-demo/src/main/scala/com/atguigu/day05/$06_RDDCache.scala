package com.atguigu.day05

object $06_RDDCache {

  /**
    * RDD持久化原因: RDD中不存储数据,如果一个rdd在多个job中重复使用,此时默认会重复执行该RDD之前的步骤,如果能将rdd的数据保存下来，后续job就不用重复执行该rdd之前的步骤了。
    * 应用场景:
    *     1、一个rdd在多个job中重复使用
    *     2、job中依赖链条太长,可以避免计算出错导致重新计算
    * 如何持久化:
    *       1、缓存: 将RDD的数据保存到分区所在机器的内存/本地磁盘中 <常用>
    *             使用方式:
    *                   rdd.cache/ rdd.persist
    *               cache与persist的区别:
    *                     cache是数据只能保存到内存中
    *                     persist可以指定将数据保存到内存/磁盘中
    *               常用的存储级别:
    *                   MEMORY_ONLY[数据只保存到内存中]： 一般用于小数据量场景
    *                   MEMORY_AND_DISK[数据一部分保存到内存中,一部分保存到磁盘中]: 一般用于大数据量场景
    *       2、checkpoint: 将RDD的数据保存到HDFS中
    *           原因: 缓存是将数据保存到本机的内存/磁盘中,如果服务器宕机，数据丢失，spark会重写启动一个task,然后根据rdd依赖关系重新计算得到数据, 影响效率。
    *           如何使用: rdd.checkpoint
    *               每个job执行完成之后,spark会查看当前job中是否有rdd需要checkpoint，如果有则将该rdd之前的数据处理步骤再次执行得到数据之后保存到指定checkpoint路径。所以checkpoint会触发一次job操作。
    *               所以如果想要避免checkpoint之前的处理重复执行可以将checkpoint操作与cache结合使用:  rdd.cache ;  rdd.checkpoint()
    *      缓存与checkpoint的区别:
    *         1、数据保存位置不一样:
    *             缓存是将RDD的数据保存到分区所在机器的内存/本地磁盘中
    *             checkpoint是将RDD的数据保存到HDFS中
    *        2、依赖关系是否保留不一样
    *            缓存是将RDD的数据保存到分区所在机器的内存/本地磁盘中,如果服务器宕机,数据丢失之后需要根据依赖关系重新计算得到数据,所以RDD的依赖关系必须保留
    *            checkpoint是将RDD的数据保存到HDFS中，数据不会丢失,所以RDD的依赖关系会删除
    */
  def main(args: Array[String]): Unit = {

    import org.apache.spark.{SparkConf, SparkContext}
    val sc = new SparkContext( new SparkConf().setMaster("local[4]").setAppName("test") )

    sc.setCheckpointDir("checkpoint")
    val rdd = sc.textFile("datas/wc.txt")

    val rdd2 = rdd.flatMap(x=>{
      println("----------------------------------")
      x.split(" ")
    })

    //val rdd21 = rdd2.cache()
    //val rdd21 = rdd2.persist()
    //缓存与checkpoint结合使用
    rdd2.cache
    rdd2.checkpoint()
    println(rdd2.toDebugString)
    val rdd3 = rdd2.map((_,1))

    val rdd6 = rdd3.glom()

    val rdd4 = rdd2.map(x=>(x,10))

    val rdd5 = rdd4.coalesce(1)
    println("-"*100)
    rdd6.collect()
    println(rdd2.toDebugString)
    rdd5.collect()
    //rdd5.collect()

    Thread.sleep(1000000)

  }
}
