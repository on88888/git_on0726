package test.c1.c3

/**
 * @Author 0726
 * @ClassName T55
 * @createTime 2021年10月22日 19:41
 */
object T55 {
  def main(args: Array[String]): Unit = {
    val array: Array[String] = Array[String]("zhangsan 25 4000","wangwu 18 6000","zhaoliu 45 1200")
    val func = (x:String)=>x.split(" ").apply(2).toInt
//    println(func(array(0)))
    println(map(array, func))
  }

  def map(array: Array[String],func:String=>Int)={

    var tmp = func(array(0))
    var tmpMax = array(0)
    for(data<-array){

      if(func(data)>tmp){
        tmp = func(data)
        tmpMax = data

      }

    }
    tmp
    tmpMax

  }
}
