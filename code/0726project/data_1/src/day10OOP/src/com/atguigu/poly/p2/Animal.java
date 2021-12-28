package day10OOP.src.com.atguigu.poly.p2;

public class Animal {

    public void eat(){
        System.out.println("动物吃饭");
    }

    public void sleep(){
        System.out.println("动物睡觉");
    }
}
class Dog extends Animal{
    @Override
    public void eat() {
        System.out.println("小狗爱吃骨头.....");
    }
    public void lookHome(){
        System.out.println("小狗看家");
    }
}
class Cat extends Animal{
    public void catchMouse(){
        System.out.println("小猫抓老鼠");
    }
    @Override
    public void eat() {
        System.out.println("小猫爱吃鱼.....");
    }
    @Override
    public void sleep() {
        System.out.println("小猫 爱睡觉.....");
    }
}
