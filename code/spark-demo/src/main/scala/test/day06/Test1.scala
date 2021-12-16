package test.day06

import org.apache.spark.RangePartitioner
import org.apache.spark.rdd.RDD

/**
 * @Author 0726
 * @ClassName Test1
 * @createTime 2021年11月03日 18:08
 */
object Test1 {

  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[Int] = sc.parallelize(List(1,3,5,6,7,8,9,3,4,5,6,2,1,3,5,2,1,1,3,4))
//    rdd1.map(x=>(x,null))
    val rdd2: RDD[(Int, Null)] = rdd1.map((_,null))

    val rdd3: RDD[(Int, Null)] = rdd2.partitionBy(new RangePartitioner(3,rdd2))

    rdd3.mapPartitionsWithIndex((index,it)=>{
      println(s"index=${index}  data=${it.toList}")
      it
    }).collect()

    Thread.sleep(1000000)

  }

}
