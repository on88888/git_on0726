package com.atguigu.chapter05

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object $03_MethodParam {

  /**
    * 方法的参数:
    *     1、默认值参数: 如果在定义方法的时候有给参数指定默认值,在调用方法的时候,有默认值的参数就可以不用传参[一般将默认值参数放在参数列表最后面]
    *         语法: def 方法名( 参数名:类型 = 默认值,.... ): 返回值类型 = { 方法体 }
    *     2、带名参数: 在调用方法的是指定将值传给哪个参数
    *     3、可变参数: 后续在调用方法的时候,参数的个数不固定
    *        语法: def 方法名( 参数名:类型,....,参数名: 类型* ): 返回值类型 = { 方法体 }
    *        注意:
    *           1、可变参数不能与默认值参数一起使用
    *           2、可变参数必须放在参数列表的最后面
    *           3、可变参数不能直接传递数组的,如果想要将集合所有元素传递给可变参数,需要通过 集合名:_* 的方式传递
    */
  def main(args: Array[String]): Unit = {

    println(add(100))
    //2、带名参数: 在调用方法的是指定将值传给哪个参数
    add2(y=100)

    println(sum(x=10, z=20, 30, 40, 50, 60))

    val arr = Array[Int](10,20,30,40)
    //sum(10,arr)
    val paths = getPaths(7, "/user/hive/warehouse/user_info")

    readFiles(paths:_*)


  }

  //1、默认值参数: 如果在定义方法的时候有给参数指定默认值,在调用方法的时候,有默认值的参数就可以不用传参
  def add(x:Int=10,y:Int=20) = x+y

  def add2(x:Int=10,y:Int) = x+y

  // 3、可变参数: 后续在调用方法的时候,参数的个数不固定
  def sum(x:Int,z:Int*) = x + z.sum

  /**
    * /user/hive/warehouse/user_info/20211019
    * /user/hive/warehouse/user_info/20211018
    * /user/hive/warehouse/user_info/20211017
    * /user/hive/warehouse/user_info/20211016
    * /user/hive/warehouse/user_info/20211015
    * /user/hive/warehouse/user_info/20211014
    * /user/hive/warehouse/user_info/20211013
    * /user/hive/warehouse/user_info/20211012
    * /user/hive/warehouse/user_info/20211011
    *
    * 需求: 统计前7天的用户注册数
    */

  /**
    * 获取指定前N天的所有目录
    * @param n 7
    * @param pathPrefix
    */
  def getPaths(n:Int,pathPrefix:String)={

    //获取当前时间
      val currentTime = LocalDateTime.now()
      for(i<- 1 to n) yield{
        //获取前i天的时间
        val time = currentTime.plusDays(-i)

        //格式化时间
        val datestr = time.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        s"${pathPrefix}/${datestr}"
      }
  }

  //模拟读取多个目录的数据
  def readFiles(path:String*) = {
    for(p<- path){
      println(p)
    }
  }
}
