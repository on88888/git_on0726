package day07OOP.src.com.atguigu.oop;

/*
类：对一类事物共有特征的描述
    模板
对象：具体的实例

一。如何创建类
    class 类名{
      属性：
          姓名
          性别
          年龄
          身高

      行为：购物
    }

二.如何声明属性
    位置： 类中方法外
       数据类型 属性名;

三.如何创建对象
     在 main()
     类名 对象名 = new 类名();
     com.atguigu.oop.Customer customer = new com.atguigu.oop.Customer();
四.如何给属性赋值
       对象名.属性名 = 值;
         customer.name="翠花";

        值要与属性的类型匹配
五.如何获取属性值？
     5.1可以将属性值赋值给一个变量
        数据类型 变量名 = 对象名.属性名;
             int age = customer.age;
     5.2直接输出属性资源
          System.out.println("customer.height = " + customer.height);



 */
public class Customer {
    String name;
    char sex;
    int age;
    double height;

    public void shopping(){
        System.out.println(name+"\t购物");
    }

}
