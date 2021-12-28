package day10OOP.src.com.atguigu.poly.p1;
/*
1.有继承关系
2.有方法重写

多态的向上转型 的前提：

    父类的引用指向子类的实例(对象)
 */
public class Test2 {
    public static void main(String[] args) {

        int a = 10;
        double d = a;
        //多态的向上转型
        Programmer pro = new China();
        pro.eat();
        //多态的向上转型
        Russia ru = new Russia();
        Programmer pro1 = ru;
        pro1.eat();
    }
}
