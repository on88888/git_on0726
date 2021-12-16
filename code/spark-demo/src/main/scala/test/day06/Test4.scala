package test.day06

import org.apache.spark.rdd.RDD


/**
 * @Author 0726
 * @ClassName Test4
 * @createTime 2021年11月03日 19:35
 */
object Test4 {

  def main(args: Array[String]): Unit = {
    import org.apache.spark.{SparkConf, SparkContext}
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("test")
    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //获取没有农贸市场的省份
    //读取数据
    val allprovincerdd = sc.textFile("datas/allprovince.txt")  //所有省份
    val productRdd = sc.textFile("datas/product.txt")

    //过滤
    val productFilterRdd: RDD[String] = productRdd.filter(line=>line.split("\t").length==6)
    //列裁剪
    val produce = productFilterRdd.map(x=>{
      val arr: Array[String] = x.split("\t")
      arr(4)
    })

    //去重
    val disProvinceRdd: RDD[String] = produce.distinct()
    val provinceKVRDD = disProvinceRdd.map(x=>(x,""))

    val allProvinceKVRDD = allprovincerdd.map(x=>(x,""))

  }
}
