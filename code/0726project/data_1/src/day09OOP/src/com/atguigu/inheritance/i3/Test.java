package day09OOP.src.com.atguigu.inheritance.i3;
/*
继承的实现： extends
     class 子类类名 extends 父类类名{

     }
注意：
 1.子类在使用资源时  先在本类找 本类中没有去父类中找 一直找到Object 为止
 2.如果一个类没有显示的继承另一个类 那么 此类默认继承自Object类
 3.java中的继承是单继承的  一个类只能直接继承另外一个类
    可以多重继承
 4.私有属性 子类不能直接使用
 5.继承表示is-a的关系
 */
public class Test {
    public static void main(String[] args) {
        Cat cat  = new Cat();

        cat.age=3;
        cat.name="小白";
        cat.eat();
        cat.catchMouse();

        cat.hashCode();

        Dog d = new Dog();

       // d.hashCode();

        System.out.println("d.name = " + d.name);


    }
}
