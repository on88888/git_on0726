package day12OOPAndException.src.com.atguigu.innerclass.in1;
/*
类的成员
属性
    普通属性
    静态属性
方法
    普通方法
    静态方法
 代码块
    普通代码块
    静态代码块
 构造器

内部类：把一个类写到另一个类的内部

    1.成员内部类  常见于源码

    2.静态成员内部类  常见于源码

    3.局部内部类 *

    4.匿名内部类  *****

 */
public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.outerMethod2();
        System.out.println("outer.name = " + outer.name);

        Outer.Inner inner = outer.new Inner();
        inner.innerMethod2();
       // 外部类名.内部类名 对象名 = new 外部类名().new 内部类名()
        Outer.Inner inner1 = new Outer().new Inner();


    }
}
