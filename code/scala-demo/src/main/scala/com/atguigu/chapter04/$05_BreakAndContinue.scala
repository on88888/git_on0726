package com.atguigu.chapter04

import scala.util.control.Breaks._
object $05_BreakAndContinue {

  /**
    * break: 结束当前循环
    * continue: 结束当次循环,开始下一次循环
    *
    * scala没有break与continue关键字
    * scala的实现break与continue:
    *     1、导入包: import scala.util.control.Breaks._
    *     2、使用breakable与break方法实现break与continue功能
    */
  def main(args: Array[String]): Unit = {


    var a=1
    //break实现

    breakable(while(a<=10){

      if(a==5)
        break()
      else{
        println(s"a=${a}")
        a=a+1
      }

    })

/*    try{
      while(a<=10){

        if(a==5)
          throw new Exception("抛出异常......")
        else{
          println(s"a=${a}")
          a=a+1
        }

      }
    }catch {
      case e:Exception =>
    }*/


    a=1
    //continue实现
    /*while(a<=10){

      try{

        if(a==5)
          throw new Exception("抛出异常......")
        else{
          println(s"a=${a}")
          a=a+1
        }
      }catch {
        case e:Exception=> a=a+1
      }

    }*/

    while(a<=10){

     breakable(
       if(a==5){
         a=a+1
         break()
       }
       else{
         println(s"a=${a}")
         a=a+1
       }
     )
    }


  }
}
