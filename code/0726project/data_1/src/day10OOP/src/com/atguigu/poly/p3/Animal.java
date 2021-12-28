package day10OOP.src.com.atguigu.poly.p3;

public class Animal {

    public void eat(){
        System.out.println("动物吃饭");
    }

}
class Dog extends Animal{
    @Override
    public void eat() {
        System.out.println("小狗吃饭");
    }
}
class Cat extends Animal{
    @Override
    public void eat() {
        System.out.println("小猫吃饭");
    }
}
