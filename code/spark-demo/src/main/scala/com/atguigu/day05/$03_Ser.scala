package com.atguigu.day05

import java.io.{FileOutputStream, ObjectOutputStream}

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Output
import org.junit.Test

import scala.beans.BeanProperty

class $03_Ser {

  /**
    * java序列化
     */
  @Test
  def javaSer(): Unit ={

    val stu = new Student
    stu.setName("zhangsan")
    stu.setAge(20)

    val oos = new ObjectOutputStream(new FileOutputStream("d:/javaser.txt"))
    oos.writeObject(stu)
    oos.flush()
    oos.close()
  }

  @Test
  def kryoSer(): Unit ={
    val stu = new Student
    stu.setName("zhangsan")
    stu.setAge(20)

    val kryo = new Kryo()

    val output = new Output(new FileOutputStream("d:/kryoser.txt"))
    kryo.writeObject(output ,stu)

    output.flush()

    output.close()
  }

}

class Student extends Serializable{

  @BeanProperty var name:String = _

  @BeanProperty var age:Int = _
}
