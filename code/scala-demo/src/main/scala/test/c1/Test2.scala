package test.c1

/**
 * @Author 0726
 * @ClassName Test2
 * @createTime 2021年10月20日 16:33
 */

import java.util
object Test2 {

  /**
   * 2、根据指定规则对数组的数据进行过滤
   * 数据: Array[Int](1,4,2,6,8,10,9,7)
   * 规则: 只保留偶数数据【动态可变】
   * 结果: Array[Int](4,2,6,8,10)
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val arr = Array(1,4,2,6,8,10,9,7)
    val func = (x:Int) => { x % 2 == 0 }
    println(map(arr,func).toList)
//    println("匿名函数："+map(arr, (x: Int) => x % 2 == 0).toList)
//    println("匿名函数："+map(arr, (x) => x % 2 == 0).toList)
//    println("匿名函数："+map(arr, _ % 2 == 0).toList)

    println(mm(arr,(x)=>if(x%2==0) x).toList)
    println(nn(arr,(x) => x % 2 == 0).toList)


  }
  def map(datas:Array[Int],func:Int=>Boolean)={

    for (data<-datas if (func(data)) ) yield {   //if在for()里面，循环守卫--保护式为true则进入循环体内部，为false则跳过，类似于continue。
        data
    }
  }

  def mm(datas:Array[Int],func:Int=>AnyVal)={
    for (ele <- datas) yield{
      func(ele)
    }
  }

  def nn(datas:Array[Int],func:Int=>Any) = {

    for (ele <- datas) yield{
      if (ele%2==0){
        ele
      }else{

      }
    }
  }

/*
def main(args: Array[String]): Unit = {
  val arr = Array(1,4,2,6,8,10,9,7)

  println(map(arr).toList)
}
  def map(datas:Array[Int])={

    for (data<-datas if (data%2==0) ) yield {
      data
    }
  }
*/

}
