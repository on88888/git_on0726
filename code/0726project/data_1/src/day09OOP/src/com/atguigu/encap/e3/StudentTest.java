package day09OOP.src.com.atguigu.encap.e3;
/*
属性直接暴露给外界 可能输出 垃圾数据
        s2.age=1200;
        s2.sex='A';
防止出现垃圾数据 对属性进行封装，
    1.属性私有
    2.提供set get 方法供外界设置属性值 以及获取属性值

   void setXxx(属性值);
   返回值 getXxx();获取属性值

    注意：
        this 区分成员变量 局部变量
        this.成员变量名；

 */



public class StudentTest {
    public static void main(String[] args) {
/*
        Student s1 = new Student();
      s1.name="张三";
        s1.age=10;
        s1.sex='男';
        int age = s1.age;
        System.out.println("名字是："+s1.name+",年龄是："+age+",性别是："+s1.sex);*/


        Student  s = new Student();

        System.out.println("s.getName() = " + s.getName());
      //  s.setName("王五");

        s.setName("赵六");

        System.out.println("s.getName() = " + s.getName());

        s.setAge(1000);

        System.out.println("s.getAge() = " + s.getAge());
//设置性别
        s.setSex('C');

        System.out.println("s.getSex() = " + s.getSex());

    }
}
