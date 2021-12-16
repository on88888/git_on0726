package com.atguigu.chapter09

import java.sql.{Connection, DriverManager, PreparedStatement}

import scala.util.Try

object $01_Exception {

  /**
    * java的异常处理:
    *     1、捕获异常: try{...}catch(Exception e){...}... finally{....}
    *     2、抛出异常: throw new XXException(...) + 方法名后面通过thorws关键字声明异常
    *
    * scala异常处理
    *      1、捕获异常:
    *         1、try{....}catch{   <只用于获取外部资源链接的时候使用>
    *                 case e2:XXException=> ....
    *                 case e:Exception=>....
    *                 ...
    *                }finally{.....}
    *         2、Try(代码块).getOrElse(默认值) [如果代码块执行成功则返回代码块的执行结果,如果代码块执行失败则返回默认值] <常用>
    *      2、抛出异常: throw new XXException(...) <不用>
    *
    */
  def main(args: Array[String]): Unit = {

    //println(m1(10, 0))
    //m2(10,0)

    //m3(10,0)

    val logs = List("1 zhangsan 20 shenzhen"," lisi 30 beijing","1 wagnwu  guangzhou")
    val list = logs.map(x=> {
      val arr = x.split(" ")
      //( arr.head , arr(1) , try{ arr(2).toInt }catch { case e:Exception=> 0 }, arr(3) )
      ( arr.head , arr(1) , Try(arr(2).toInt).getOrElse( 0 ), arr(3) )
    })
    println(list)
  }


  def m1(a:Int,b:Int) = {
    a/b
  }

  //捕获异常
  def m2(a:Int,b:Int) = {
    try{
      a/b
    }catch {
      case e:ArithmeticException =>
        println("被除数不能为0")
      case e:Exception => println("其他异常")
    }

  }

  //抛出异常
  def m3(a:Int,b:Int)  = {
    if(b==0) throw new Exception("......")
    a/b

  }

  def jdbcUtil(): Unit ={

    var connection: Connection = null
    var statement: PreparedStatement = null
    try{
      //创建连接
      connection = DriverManager.getConnection("jdbc:mysql://hadoop102:3306/test")
      //创建statement对象
      statement = connection.prepareStatement("insert into person values(?,?,?)")
      //赋值
      statement.setString(1,"lisi")
      statement.setInt(2,20)
      statement.setString(3,"shenzhen")
      //执行sql
      statement.executeUpdate()
    }catch {
      case e:Exception =>
    }finally {
      //关闭资源链接
      if(statement!=null)
        statement.close()
      if(connection!=null)
        connection.close()
    }


  }
}
