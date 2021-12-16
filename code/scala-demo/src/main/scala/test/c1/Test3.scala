package test.c1

/**
 * @Author 0726
 * @ClassName Test3
 * @createTime 2021年10月20日 17:35
 */
object Test3 {

  /**
   * 3、根据指定规则对数组所有元素进行聚合
   * 数据: Array[Int](10,20,30,40)
   * 规则: 求和
   * 结果: 100
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val arr = Array[Int](10,20,30,40)
    val func =(a:Int,b:Int)=>a+b
    println(sum(arr,func))
  }
    def sum (arr:Array[Int],func:(Int,Int)=>Int):Int = {
      //var a = 0
      var tmp = arr(0)
      for(index<-1 until arr.length) {
        tmp = func(tmp,arr(index))
      }
      tmp
    }

}
