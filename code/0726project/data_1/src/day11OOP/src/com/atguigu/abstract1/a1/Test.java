package day11OOP.src.com.atguigu.abstract1.a1;
/*
抽象：既不具体 也无法具体
抽象类：abstract class 类名{}
抽象方法： 规定子类的行为
        权限修饰符 abstract 返回值类型 方法名(形参);

用来模拟 is a的关系

 */
public class Test {
    public static void main(String[] args) {


        Dog d  = new Dog();
        d.eat();

        Animal a = new Dog();

        a.eat();

     //   Animal an = new Animal();

       // an.eat();

        Animal c = new Cat();
        c.eat();
    }
}
