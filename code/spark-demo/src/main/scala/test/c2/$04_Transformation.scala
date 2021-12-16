package test.c2

import org.apache.spark.{HashPartitioner, Partitioner}
import org.apache.spark.rdd.RDD
import org.junit.Test

/**
 * @Author 0726
 * @ClassName $Test
 * @createTime 2021年11月01日 18:20
 */
class $04_Transformation {
  import org.apache.spark.{SparkConf, SparkContext}
  //1.创建SparkConf并设置App名称
  val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
  //2.创建SparkContext，该对象是提交Spark App的入口
  val sc: SparkContext = new SparkContext(conf)

  /**
   * intersection : 交集
   */
  @Test
  def intersection(): Unit ={
    val rdd1: RDD[Int] = sc.parallelize(List(1,3,5,7,9))
    val rdd2: RDD[Int] = sc.parallelize(List(1,2,3,4,5,6))
    val rdd3: RDD[Int] = rdd1.intersection(rdd2)
    println(rdd3.getNumPartitions) //获取分区数
    println(rdd3.collect().toList)
  }

  /**
   * subtract : 差集
   */
  @Test
  def subtract() :Unit ={
    val rdd1: RDD[Int] = sc.parallelize(List(1,3,5,7,9))
    val rdd2: RDD[Int] = sc.parallelize(List(1,2,3,4,5,6))
    val rdd3: RDD[Int] = rdd1.subtract(rdd2)

    println(rdd3.collect().toList)
  }

  /**
   * union : 并集
   *      union生成的新的RDD的分区数 = 父RDD分区数之和
   */
  @Test
  def union(): Unit ={
    val rdd1: RDD[Int] = sc.parallelize(List(1,3,5,7,9))
    val rdd2: RDD[Int] = sc.parallelize(List(1,2,3,4,5,6))
    val rdd3: RDD[Int] = rdd1.union(rdd2)

    println(rdd3.getNumPartitions)
    println(rdd3.collect().toList)
  }

  /**
   * zip : 拉链
   *    两个RDD要想拉链 <必须元素个数与分区数都相同>
   */
  @Test
  def zip(): Unit ={
    val rdd1: RDD[String] = sc.parallelize(List("lisi","zhangsan","aliyun","zhaoliu"),2) //numSlices
    val rdd2: RDD[Int] = sc.parallelize(List(11,22,33,44),2)
    val rdd3: RDD[(String, Int)] = rdd1.zip(rdd2)

    println(rdd3.getNumPartitions)
    println(rdd3.collect().toList)
  }

  /**
   * partitionBy: 根据指定的分区器重分区
   */
  @Test
  def partitionBy(): Unit ={
    val rdd1: RDD[Int] = sc.parallelize(List(1,3,4,5,6))
    val rdd2: RDD[(Int, Int)] = rdd1.map(x=>(x,x))
    //println(rdd2.collect().toList)

    rdd2.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index}  data=${it.toList}")
      it
    }).collect()

    val rdd3: RDD[(Int, Int)] = rdd2.partitionBy(new HashPartitioner(6))
    println(rdd3.getNumPartitions)

    rdd3.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index}  data=${it.toList}")
      it
    }).collect()
  }

  /**
   * 自定义分区器：
   *      1、定义一个class继承Partitioner抽象类
   *      2、重写抽象方法
   */
  @Test
  def partitioner(): Unit ={
    val rdd1: RDD[Int] = sc.parallelize(List(1,3,4,5,2,7,8,9))
    val rdd2: RDD[(Int, Iterable[Int])] = rdd1.groupBy(x=>x,p = new MyPartitioner(6))

    rdd2.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index}  data${it.toList}")
      it
    }).collect()
  }

  /**
   * groupByKey: 根据Key分组
   *      groupByKey生成的新RDD的元素是KV键值对
   *          K: 是分组的K
   *          V: 是K在原RDD中对应的所有的value值的集合
   */
  @Test
  def groupByKey(): Unit ={
    val rdd1: RDD[(String, Int)] = sc.parallelize(List( ("aa",11),("bb",22),("cc",33),"aa"->44,"dd"->55,"cc"->66 ))
    val rdd2: RDD[(String, Iterable[Int])] = rdd1.groupByKey()

    println(rdd2.collect().toList)
  }

  /**
   * reduceByKey(func: (value值类型,value值类型)=>value值类型 )：按照key分组之后,对每个组所有的value值聚合
   */
  @Test
  def reduceByKey(): Unit ={
    val rdd1: RDD[String] = sc.textFile("datas/wc.txt")

    val rdd: RDD[Array[String]] = rdd1.map(x=>x.split(" "))
    val rdd2 = rdd1.flatMap(x=>x.split(" "))
//    println(rdd2.collect().toList)
    //val rdd3 = rdd2.groupBy(x=>x)
    //val rdd4 = rdd3.map(x=>(x._1,x._2.size))

    val rdd3: RDD[(String, Int)] = rdd2.map(x=>(x,1))
    println(rdd3.collect().toList)

    rdd3.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()

    val rdd4 = rdd3.reduceByKey( (agg,curr)=>{
      println(s"agg=${agg} curr=${curr}")
      agg+curr
    } )

    println(rdd4.collect().toList)
  }

  @Test
  def combineByKey(): Unit ={
    val rdd = sc.parallelize(List( ("语文",64),("英语",100),"英语"->80,"数学"->100,"英语"->70,"语文"->70,"数学"->80,"语文"->100,"数学"->80,"英语"->60,"英语"->50,"数学"->100,"数学"->100,"语文"->100 ,"英语"->80),2)
    //需求: 统计每门学科的平均分
    val rdd2: RDD[(String, (Int, Int))] = rdd.map(x=>(x._1,(x._2,1)))

    val rdd3: RDD[(String, (Int, Int))] = rdd2.reduceByKey( (agg,curr)=>(agg._1+curr._1,agg._2+curr._2) )
    println(rdd3.collect().toList)

    val rdd4 = rdd3.map {
      case (name, (score,num))=> (name, score / num)
    }.collect()toList

    println(rdd4)

    //---------------------------------------------------------------------------------------

    rdd.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index} data=${it.toList}")
      it
    }).collect()

    val rdd7 = rdd.combineByKey(x=> (x,1) , (agg:(Int,Int) ,curr: Int) => {
      println(s"combiner阶段: agg=${agg} curr=${curr}")
      (agg._1+curr, agg._2+1)
    } , (agg: (Int,Int) ,curr:(Int,Int))=> {
      println(s"reducer阶段: agg=${agg} curr=${curr}")
      (agg._1+curr._1, agg._2+curr._2)
    })
    val rdd8 = rdd7.map{
      case (name,(score,num)) => (name,score/num)
    }
    println(rdd8.collect().toList)

  }












}

/**
 * 自定义分区器：
 *      1、定义一个class继承Partitioner抽象类
 *      2、重写抽象方法
 */
class MyPartitioner(num:Int) extends Partitioner{

  /**
   * 返回重分区的分区数
   * @return
   */
  override def numPartitions: Int = {
    if(num<6)
      6
    else
      num
  }

  /**
   * 根据key获取分区号
   * @param key
   * @return
   */
  override def getPartition(key: Any): Int = key match {
    case 1 => 2
    case 2 => 4
    case 3 => 1
    case 4 => 3
    case _ => 0
  }
}
