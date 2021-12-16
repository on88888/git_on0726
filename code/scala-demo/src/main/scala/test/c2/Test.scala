package test.c2

/**
 * @Author 0726
 * @ClassName Test
 * @createTime 2021年10月23日 08:29
 */
object Test {
  def main(args: Array[String]): Unit = {

    //（1）通过伴生对象的apply方法，实现不使用new关键字创建对象。
    val p1 = Person()
    println("p1.name=" + p1.name)

    val p2 = Person("bobo")
    println("p2.name=" + p2.name)

    Person.apply()
    Person()
    Person.apply("aaa")
    Person("aaa")
  }
}

//（2）如果想让主构造器变成私有的，可以在()之前加上private
class Person private(cName: String) {
  var name: String = cName
}

object Person {

  def apply(): Person = {
    println("apply空参被调用")
    new Person("xx")
  }

  def apply(name: String): Person = {
    println("apply有参被调用")
    new Person(name)
  }

}
