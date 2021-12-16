package test.c2

import scala.beans.BeanProperty

/**
 * @Author 0726
 * @ClassName Test111
 * @createTime 2021年10月22日 09:53
 *
 *      自动创建生成变量--快捷键
 *      Ctrl+Alt+V
 *      Atl+Shift+L
 */
object Test111 {
  class Person{

    @BeanProperty var name:String = _
    @BeanProperty var age:Int = _

  }
  def main(args: Array[String]): Unit = {

    import java.util.{HashMap=>JAVAHashMap}

//    new JAVAHashMap[String]()

    val person = new Person
    person.setName("zhangsan")
    person.setAge(22)

    println(person.getName)
    println(person.getAge)

    //val json = JSON.toJSONString()
    new Person

    val a = "zhangsan"

    println(a.charAt(2))


    val array: Array[Int] = Array.apply[Int](2,3,5,7)
    println(array.toList)


  }

}
