package test.c1.c3

/**
 * @Author 0726
 * @ClassName T11
 * @createTime 2021年10月22日 18:52
 */
object T11 {
  def main(args: Array[String]): Unit = {

    val array: Array[String] = Array[String]("spark","hadoop","flume","kafka")
    val func =(x:String) => x.length
    println(map(array, func).toList)
  }

  def map(arr:Array[String],func:String=>Int)={

    for(data<- arr) yield{
      func(data)
    }
  }
}
