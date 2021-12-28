package day07OOP.src.com.atguigu.oop;

import java.util.Scanner;

/*
陈科百
    联系项目
    开发  程序员
    测试  测试
    运维
    售后
    行政  秘书
 */
public class OOPTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //创建对象
        Customer customer = new Customer();
        //对当前对象的属性赋值
        customer.name="翠花";
        customer.sex='女';
        customer.age=19;
        customer.height=1.66;
        //获取当前对象的属性值
        String name =  customer.name;
        System.out.println("name = " + name);
        int age = customer.age;
        System.out.println("age = " + age);
        System.out.println("customer.height = " + customer.height);

        //展示行为 调用方法
        customer.shopping();


        System.out.println("---------------------------------");
       // 类名 对象名 = new 类名();
        Customer c = new Customer();

        //给属性赋值
        c.name="小芳";
        c.age=19;

        //获取属性值
        String name1 = c.name;
        System.out.println("name1 = " + name1);
        System.out.println("c.age = " + c.age);

        c.shopping();


    }
}
