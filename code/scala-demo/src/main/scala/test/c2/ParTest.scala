package test.c2

/**
 * @Author 0726
 * @ClassName ParTest
 * @createTime 2021年10月26日 09:14
 */
object ParTest {

  def main(args: Array[String]): Unit = {

    val list = List(1,3,5,7,8)

    list.foreach(x=>{
      println(s"${Thread.currentThread().getName}")
    })

    println("-"*100)

    list.par.foreach(x=>{
      println(s"${Thread.currentThread().getName}")
    })
  }

}
