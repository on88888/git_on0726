package test.c2

import org.junit.Test

import scala.beans.BeanProperty

/**
 * @Author 0726
 * @ClassName $05_Ser
 * @createTime 2021年11月02日 10:35
 */
object $05_Ser {


  @Test
  def javaSer(): Unit ={
    val stu: Student = new Student
    stu.setName("zhangsan")
    stu.setAge(20)


  }

}

class Student extends Serializable{

  @BeanProperty var name:String = _
  @BeanProperty var age:Int = _

}
