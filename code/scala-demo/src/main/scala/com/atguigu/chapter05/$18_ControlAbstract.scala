package com.atguigu.chapter05

object $18_ControlAbstract {

  /**
    * 控制抽象:
    *     语法: =>返回值类型
    *     控制抽象不能单独使用,只能作为方法的参数存在
    *     控制抽象其实就是一个<块表达式>,控制抽象中返回值类型就是<块表达式>的结果值类型,后续看到方法的参数类型是控制抽象的时候,其实就是让你传一个块表达式。
    *     控制抽象可以当做函数调用,调用的时候不能带上()，调用一次,控制抽象的块表达式就执行一次
    */
  def main(args: Array[String]): Unit = {

    val b = {

      println("-------------------")
      10
    }

    m1(b)
    m1(b)
    m1(b)


    val func:()=>Int = () => {

      println("++++++++++++++++")
      10
    }
    m2(func)
    m2(func)
    m2(func)

    m3( b )


    def myWhile(condition: => Boolean)(loop: => Unit):Unit={

      if(condition){
        loop
        myWhile(condition)(loop)
      }
    }

    var a=1
    myWhile( a<=10 ){
      println(s"a=${a}")
      a=a+1
      a
    }

  }

  def m1(x:Int) = {
    println(x)
  }


  def m2(func: ()=>Int) = {
    func()

  }

  def m3(func: =>Int) = {
    func
    func
    func
  }


}
