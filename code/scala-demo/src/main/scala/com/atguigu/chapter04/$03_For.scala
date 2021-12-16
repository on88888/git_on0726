package com.atguigu.chapter04

object $03_For {

  /**
    * for循环的两个方法:
    *     to方法:
    *       语法: start.to(end) / start to end
    *       to方法生成的是一个左右闭合的集合
    *     until
    *       语法: start.until(end) / start until end
    *       until方法生成的是一个左闭右开的集合
    *
    * for循环的基本语法: for(变量<- 数组/集合/表达式){....}
    * for循环的守卫: for(变量<-  数组/集合/表达式  if(布尔表达式)){ ... }
    * 步长: for(变量<- start to/until end by step){...}
    * for循环的嵌套: for(变量<- 数组/集合/表达式 ; 变量<- 数组/集合/表达式 ; .... ){...}
    * 引入变量: for(变量<- 数组/集合/表达式 ; 变量2 = 值 ;变量<- 数组/集合/表达式 ; .... ){...}
    * yield表达式:
    *   for循的循环体的{}默认不能看做块表达式,默认没有返回值
    *   想要让for循环有返回值需要使用yield表达式
    *   语法:  for(变量<- 数组/集合/表达式) yield{....}
    *
    */
  def main(args: Array[String]): Unit = {

    for( i<- 1 to 10){
      println(s"i=${i}")
    }
    println("*"*100)
    for(i<- 1 to 10){
      //println(s"i+i=${i+i}")
      if(i%2==0){
        println(s"i=${i}")
      }
    }
    println("+"*100)
    //for循环的守卫: for(变量<-  数组/集合/表达式  if(布尔表达式)){ ... }
    for(i<- 1 to 10 if(i%2==0)){
      //println(s"i+i=${i+i}")
      println(s"i=${i}")
    }

    println("="*100)
    for(j<- 1.to(10).by(3) ){
      println(s"j=${j}")
    }
    println("*"*100)
    //嵌套for循环
    for(i<- 0 to 10){
      val k = i*i
      for(j<- i to k){

        println(s"i+j=${i+j}")
      }
    }
    println("="*100)
    for(i<- 0 to 10 ; k = i*i ;j<- i to k){
      println(s"i+j=${i+j}")
    }

    //yield表达式:
    val r  = for(k<- 1 to 10) yield {
      k*k
    }
    println(r)


    //(1 to 10).foreach(println)
  }
}
