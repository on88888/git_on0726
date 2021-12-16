package test.c1.c3

/**
 * @Author 0726
 * @ClassName T33
 * @createTime 2021年10月22日 19:01
 */
object T33 {
  def main(args: Array[String]): Unit = {
    val array: Array[Int] = Array[Int](10,20,30,40)
    val func =(x:Int,y:Int) =>x+y

    println(sum(array, func))
  }

  def sum(datas:Array[Int],fenc:(Int,Int)=>Int)={

    var tmp = datas(0)
    for(i<- 1 until datas.length){
      tmp = tmp + datas(i)
    }
    tmp
  }
}
