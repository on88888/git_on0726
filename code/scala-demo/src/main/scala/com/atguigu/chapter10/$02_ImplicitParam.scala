package com.atguigu.chapter10

object $02_ImplicitParam {

  /**
    * 隐式参数: 方法调用的时候悄悄的给方法传参
    *     语法:
    *         1、定义方法的时候指定后续哪个参数会自动传参: def 方法名(参数名:类型,...)(implicit 参数名:类型):返回值类型 = {....}
    *         2、定义一个隐式值: implicit val 参数名:类型 = 值
    */
  def main(args: Array[String]): Unit = {

    import com.atguigu.chapter01.ImplicitsTest.a
    m1(10,20)(40)
    m2(300,400)
    m3(1,2)
    m4(3,4)
    m5(5,6)

  }



  def m1(x:Int,y:Int)(implicit z:Int) = {
    x+y+z
  }
  def m2(x:Int,y:Int)(implicit z:Int) = {
    x+y-z
  }
  def m3(x:Int,y:Int)(implicit z:Int) = {
    x+y*z
  }
  def m4(x:Int,y:Int)(implicit z:Int) = {
    x+y/z
  }
  def m5(x:Int,y:Int)(implicit z:Int) = {
    x-y+z
  }
}
