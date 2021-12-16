package com.atguigu

import scala.io.Source

object Test {

  def main(args: Array[String]): Unit = {

    //读取数据
    val allProvinces = Source.fromFile("datas/allprovince.txt","UTF-8").getLines().toList

    val products = Source.fromFile("datas/product.txt","UTF-8").getLines().toList
    val filterList = products.filter(line=> line.split("\t").length==6)
    //m1(allProvinces,filterList)
    //m2(filterList)
    m3(filterList)

    /**
     * <定位异常数据>
     */
    /*
    val productMap = product.map(y =>{
      try{
        y.split("\t")(4)
      }catch{
        case e:Exception=> println(y)
      }
    })*/
  }

  /**
    * 统计没有农贸市场的省份
    */
  def m1(allProvinces:List[String],filterList:List[String]): Unit ={

    //1、是否要去重【去重】、是否要过滤【过滤脏数据】、是否要列裁剪[省份]
    //1.1、过滤

    //1.2、列裁剪
    val provinces = filterList.map(line=>{

        val arr = line.split("\t")
        arr(4)

    })
    //1.3、去重
    val disProvinceList = provinces.distinct
    //List(山西省,湖南省,湖北省)
    //2、将所有省份与有农贸市场的省份取差集
    val result = allProvinces.diff(disProvinceList)
    //3、结果展示
    result.foreach(println)

  }

  /**
    * 统计菜的种类数最多的三个省份
    */
  def m2(products:List[String] ): Unit ={

    //1、列裁剪[菜名、省份]
    val selectList = products.map(line=>{
      val arr = line.split("\t")
      ( arr(4),arr(0) )
    })
    //2、去重
    val disList = selectList.distinct
    //List( (湖北省,西红柿),(湖北省,西蓝花),(湖南省,小白菜),(湖北省,鲫鱼),... )
    //3、按照省份分组
    val groupedMap = disList.groupBy(_._1 )
//    val groupedMap = disList.groupBy(x=>x._1 )
    //Map(
    //    湖北省 -> List(  (湖北省,西红柿),(湖北省,西蓝花),(湖北省,鲫鱼),... )
    //    湖南省 -> List(  (湖南省,小白菜),... )
    //
    // )
    //4、统计每个省份菜的种类数
    val countList = groupedMap.map(x=>{
      //x = 湖北省 -> List(  (湖北省,西红柿),(湖北省,西蓝花),(湖北省,鲫鱼),... )

      (x._1, x._2.size )
    })

    //5、排序取前三
    val top3 = countList.toList.sortBy( _._2).reverse.take(3)
//    val top3 = countList.toList.sortBy( x=>x._2).reverse.take(3)

    //6、结果展示
    top3.foreach(println)
  }

  /**
    * 统计每个省份菜的种类数最多的三个农贸市场
    */
  def m3( products:List[String]): Unit ={

    //1、列裁剪[ 省份、农贸市场、菜名]
    val selectList = products.map(line=>{
      val arr = line.split("\t")
      (arr(4), arr(3) , arr(0))
    })
    //2、去重
    val disList = selectList.distinct
    //List( (湖北省,A农贸市场,西红柿),(湖北省,B农贸市场,西红柿),(湖北省,A农贸市场,大白菜),(湖北省,A农贸市场,鲫鱼),(湖北省,C农贸市场,西红柿),(湖南省,T农贸市场,西红柿)... )
    //3、按照省份+农贸市场分组
    val groupedMap = disList.groupBy{
      case (province,market,name) => (province,market)
    }
    //Map(
    //    (湖北省,A农贸市场) -> List( (湖北省,A农贸市场,西红柿), (湖北省,A农贸市场,大白菜),(湖北省,A农贸市场,鲫鱼),...)
    //    (湖北省,B农贸市场) -> List( (湖北省,B农贸市场,西红柿),,...)
    // )
    //4、统计每个省份每个农贸市场的菜的种类数
    val provinceMarketCountList = groupedMap.map(x=>{
      //x = (湖北省,A农贸市场) -> List( (湖北省,A农贸市场,西红柿), (湖北省,A农贸市场,大白菜),(湖北省,A农贸市场,鲫鱼),...)
      (x._1,x._2.size)
    })
    //List( ((湖北省,A农贸市场),15),((湖北省,B农贸市场),20),((湖北省,C农贸市场),5),((湖南省,T农贸市场),55),.... )
    //5、按照省份分组
    val provinceGroupedMap = provinceMarketCountList.groupBy{
      case ( (province,market),num ) => province
    }
    //Map(
    //      湖北省 -> List( ((湖北省,A农贸市场),15),((湖北省,B农贸市场),20),((湖北省,C农贸市场),5),.... )
    //      ....
    // )
   // println(provinceGroupedMap)
    //6、对每个省份所有农贸市场数据按照种类数排序，取前三
    provinceGroupedMap.map(x=>{
      //x = 湖北省 -> List( ((湖北省,A农贸市场),15),((湖北省,B农贸市场),20),((湖北省,C农贸市场),5),.... )
      val top3 = x._2.toList.sortBy(_._2).reverse.take(3)
//      val top3 = x._2.toList.sortBy(x=>x._2).reverse.take(3)

      val r = top3.map{
        case ( (province,market),num ) => (market,num)
      }

      (x._1, r)
    })
    //7、结果展示
      .foreach(println)
  }
}
