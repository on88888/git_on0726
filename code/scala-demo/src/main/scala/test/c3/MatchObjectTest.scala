package test.c3

/**
 * @Author 0726
 * @ClassName MatchObjectTest
 * @createTime 2021年10月26日 10:11
 */
object
MatchObjectTest {

  case class Person(val name:String,var age:Int ,address:String)

  def main(args: Array[String]): Unit = {

    val person = Person("zhangsan",20,"shenzhen")
    println(person)

//    person.name = "xx"
//    person.address = "beijing"

    person match {
      case Person(x,y,z) => println(s"x=${x} y=${y} z=${z}")
    }



  }
}
