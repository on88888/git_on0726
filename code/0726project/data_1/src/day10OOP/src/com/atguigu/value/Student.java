package day10OOP.src.com.atguigu.value;
/*
默认值
显示赋值
构造器赋值
set  对象名.属性名  类名.属性名
 */
public class Student {

    String name="A";
  static   int age=20;

    public Student(String name,int age) {
        this.name = name;
        Student.age=age;
    }
}
