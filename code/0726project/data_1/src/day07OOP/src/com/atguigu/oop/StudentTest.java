package day07OOP.src.com.atguigu.oop;/*
对象的内存分配：
方法区： 存储 类的信息 方法的信息 属性信息 常量信息 静态信息
元空间：不在占用虚拟机内存 直接占用 物理内存


本地方法栈： 本地方法执行时

虚拟机栈： 方法调用 相关信息

堆： 对象  数组

程序计数器：下一条指令的地址


 */

public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.age=10;
        s1.name="张三";
        s1.score=89.3;
        System.out.println("s1.name = " + s1.name);
        System.out.println("s1.score = " + s1.score);
        System.out.println("s1.age = " + s1.age);

        s1 = new Student();
        s1.name="李白";

        System.out.println("-----------");
        System.out.println("s1.name = " + s1.name);
        System.out.println("s1.score = " + s1.score);
        System.out.println("s1.age = " + s1.age);


       /* com.atguigu.oop.Student s2 = new com.atguigu.oop.Student();
        System.out.println("s2.name = " + s2.name);
        System.out.println("s2.age = " + s2.age);
        System.out.println("s2.score = " + s2.score);*/



    }
}
