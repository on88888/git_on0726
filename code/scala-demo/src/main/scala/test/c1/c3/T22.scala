package test.c1.c3

/**
 * @Author 0726
 * @ClassName T22
 * @createTime 2021年10月22日 18:54
 */
object T22 {
  def main(args: Array[String]): Unit = {

    val array = Array(1,4,2,6,8,10,9,7,6)
    val func = (x:Int)=>x%2==0
    println(num(array, func).toList)
    println(num(array, (x: Int) => x % 2 == 0).toList)
    println(num(array, (x) => x % 2 == 0).toList)
    println(num(array, _% 2 == 0).toList)
  }

  def num(array: Array[Int],func:(Int)=>Boolean)={

    for(data<- array if(func(data))) yield{
      data
    }

  }

}
