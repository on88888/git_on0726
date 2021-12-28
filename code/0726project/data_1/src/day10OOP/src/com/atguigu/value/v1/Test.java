package day10OOP.src.com.atguigu.value.v1;
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




 */
public class Test {
    public static void main(String[] args) {

        Dog dog = new Dog();

        //dog.show();
        System.out.println("---------------------");
        Dog dog1 = new Dog("旺财");

    }
}
