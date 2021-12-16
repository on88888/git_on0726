package test.c3

/**
 * @Author 0726
 * @ClassName Test
 * @createTime 2021年10月26日 11:28
 */
object Test {

  case class Person(val name:String,var age:Int,address:String)

  abstract class Sex

  case object Man extends Sex
  case object Woman extends Sex

  def m1(sex: Sex): Unit ={
    println(sex)
  }

  def main(args: Array[String]): Unit = {

    val person = Person("zhangsan",20,"shenzhen")
    println(person)

    m1(Man)

    person match{
      case Person(x,y,z) =>println(s"x=${x} y=${y} z${z}")
    }
    
    val stu = Student("lisi",30,"beijing")

    stu match{
      case Student(x,y,z) =>println(s"x=${x} y=${y} z${z}")
    }


    val list2 = List(
      ("宝安区",("宝安中学",("法师班",("安其拉",20)))),
      ("宝安区",("宝安中学",("法师班",("王昭君",20)))),
      ("宝安区",("宝安中学",("法师班",("甄姬",20)))),
      ("宝安区",("宝安中学",("法师班",("小乔",20))))
    )

    list2.foreach(x=>{
      x match {
        case (addName,(schoolName,(className,(sName,age)))) => println(sName)
      }
    })


    /*    val t = ("lisi",20,"shenzhen")
        t match {
          case (x, y, z) => println(s"x=${x} y=${y} z=${z}")
        }

        val t2 = List(
          ("宝安区",("宝安中学",("法师班",("安其拉",20)))),
          ("宝安区",("宝安中学",("法师班",("王昭君",20)))),
          ("宝安区",("宝安中学",("法师班",("甄姬",20)))),
          ("宝安区",("宝安中学",("法师班",("小乔",20))))
        )

        t2.foreach(x=>{
          x match {
            case (regionName,(schoolName,(clazzName,(stuName,age)))) =>println(stuName)
          }
        })*/

    /*    val t1 = ("zhangsan",20,"shenzhen")
        println(t1._1)

        val (name,age,_)=("zhangsan",20,"shenzhen")
        println(name)

        val map = Map("aa"->11,"bb"->22,"cc"->33)
        for ((k,v)<-map){
          println(k)
          println(v)
        }*/


  }
}
class Student(val name:String,var age:Int,val address:String){

}

object Student{
  def apply(name:String,age:Int,address:String) = new Student(name,age,address)

  def unapply(arg: Student): Option[(String, Int, String)] = {

    if(arg==null)
      None
    else
      Some( (arg.name,arg.age,arg.address) )

  }
}