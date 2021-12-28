package day10OOP.src.com.atguigu.value.v2;
/*
普通成员代码块：
        {}  等价于 普通的方法
        位置：类中方法外
        作用：给普通成员变量赋值
    注意：
        1.代码块 有作用域限制
        2.代码块先于构造器执行
        3.创建一次对象 代码块就会执行一次

静态成员代码块
     static {} 等价于 静态的方法
      位置：类中方法外
      作用：给静态成员变量赋值
   注意：
     1.代码块 有作用域限制
     2.代码块先于普通代码块执行
     3.无论创建多少次对象 代码块只执行一次

 */
public class Test {
    public static void main(String[] args) {

        Person p1 = new Person();
        System.out.println("--------");

        Person p2 = new Person();

    }
}
