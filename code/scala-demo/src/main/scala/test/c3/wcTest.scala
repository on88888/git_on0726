package test.c3

import scala.io.Source


/**
 * @Author 0726
 * @ClassName wcTest
 * @createTime 2021年10月25日 18:13
 */
object wcTest {
  /**
   * 1、统计哪些省份没有农贸市场
   * 2. 获取农产品种类最多的三个省份
   * 3. 获取每个省份农产品种类最多的三个农贸市场
   */
  def main(args: Array[String]): Unit = {

    val province: List[String] = Source.fromFile("datas/allprovince.txt").getLines().toList
    //println(province)
    val product = Source.fromFile("datas/product.txt").getLines().toList
    //println(product)

    /**
     * <定位异常数据>
     */
/*    //定位异常数据
    val productMap = product.map(y =>{
      try{
        y.split("\t")(4)
      }catch{
        case e:Exception=> println(y)
      }
    })*/

//    test1(product,province)
//    test2(product)
//    test3(product)


    /**
     * 统计没有农贸市场的省份
     */
/*    //过滤垃圾数据
    val filterProduct: List[String] = product.filter((x:String) => x.split("\t").size==6)
    println(filterProduct)
    //取省份字段
    val splitProduct: List[String] = filterProduct.map(y => y.split("\t")(4))
    println(splitProduct)

    //去重
    val distinctProduct: List[String] = splitProduct.distinct
    println(distinctProduct)
    //取差
    val diffProduct: List[String] = province.diff(distinctProduct)
    println(diffProduct)
    //展示结果
    diffProduct.foreach(println)*/


    /**
     * 统计菜的种类数最多的三个省份
     */
/*    //过滤垃圾数据
    val filterProduct: List[String] = product.filter(x=>x.split("\t").size==6)
    //转化为省份与菜品集合
    val map = filterProduct.map(x=>(x.split("\t")(4),x.split("\t")(0)))
    println(map)
    //去重
    val distinctMap: List[(String, String)] = map.distinct
    println(distinctMap)
    //按省份分组
    val groupMap: Map[String, List[(String, String)]] = distinctMap.groupBy(x=>x._1)
    println(groupMap)
    //求value元素的个数，即菜品种类，组成一个新集合
    val lenghtMap = groupMap.map(x=>(x._1,x._2.size))
    println(lenghtMap)
    //转化为list集合，使用它的方法
    val listProduct: List[(String, Int)] = lenghtMap.toList
    println(listProduct)
    //排序
    val sortProduct: List[(String, Int)] = listProduct.sortBy(x=>x._2).reverse
    println(sortProduct)
    //获取前三个元素
    val takeProduct = sortProduct.take(3)
    println(takeProduct)
    //展示
    takeProduct.foreach(println)
    */

    /**
     * 统计每个省份菜的种类数最多的三个农贸市场
     */

/*    //1、列裁剪[ 省份、农贸市场、菜名]
    //过滤
    val filterProduct: List[String] = product.filter(x=>x.split("\t").length==6)
    val mapProduct = filterProduct.map(x=>{
      val func = x.split("\t")
      (func(4),func(3),func(0))
    })
    //2、去重
    val distinctProduct: List[(String, String, String)] = mapProduct.distinct
    //3、按照省份+农贸市场分组
    val groupList = distinctProduct.groupBy(x=>{
      (x._1,x._2)
    })
    //4、统计每个省份每个农贸市场的菜的种类数
    val countList =  groupList.map(x=>(x._1,x._2.size))
    //5、按照省份分组
    val groupProduct = countList.groupBy{
      case( (pro,mar),num )=>pro
    }
    //6、对每个省份所有农贸市场数据按照种类数排序，取前三
    groupProduct.map(x=>{
      val take3 = x._2.toList.sortBy(x=>x._2).reverse.take(3)
      val t3 = take3.map{
        case ((sf,sc),n)=>(sc,n)
      }
      (x._1,t3)
    })
    //7、结果展示
      .foreach(println)*/


  }

  def test1(product:List[String], province:List[String])={
    province.diff(product.filter(x=>x.split("\t").size==6)
      .map(x=>x.split("\t")(4))
      .distinct)
      .foreach(println)
  }

  def test2(product:List[String])={
    product.filter(x=>x.split("\t").size==6)
      .map(x=>(x.split("\t")(4),x.split("\t")(0)))
      .distinct
      .groupBy(x=>x._1)
      .map(x=>(x._1,x._2.size))
      .toList
      .sortBy(x=>x._2).reverse
      .take(3)
      .foreach(println(_))
  }

  def test3(product:List[String])={
    product.filter(x=>x.split("\t").size==6)
      .map(x => {
        val arr = x.split("\t")
        (arr(4), arr(3), arr(0))  //(省份, 市场, 品种)
      })
      .distinct
      .groupBy(x => {
        (x._1, x._2)    //根据省份+市场分组
      })
      .map(x=>(x._1,x._2.size))
      .groupBy(x=>x._1._1)
      .map(x => {
        val take3 = x._2.toList.sortBy(_._2).reverse.take(3)  //*****
        (x._1, take3)
      })
      .foreach(println)
  }


}
