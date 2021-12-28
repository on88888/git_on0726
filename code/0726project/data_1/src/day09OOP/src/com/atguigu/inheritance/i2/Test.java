package day09OOP.src.com.atguigu.inheritance.i2;
/*
继承的实现： extends
     class 子类类名 extends 父类类名{

     }



 */
public class Test {
    public static void main(String[] args) {

        Cat cat = new Cat();
        cat.eat();
        cat.catchMouse();

        Dog d = new Dog();
        d.lookHome();
        d.eat();
    }
}
