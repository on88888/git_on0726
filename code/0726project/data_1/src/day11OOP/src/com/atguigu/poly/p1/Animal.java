package day11OOP.src.com.atguigu.poly.p1;

public class Animal {
    public void eat(){
        System.out.println("动物吃饭");
    }
}
class Dog extends Animal{
    public void eat(){
        System.out.println("小狗爱吃骨头");
    }
    public void lookHome(){
        System.out.println("小狗看家");
    }
}
class Cat extends Animal{
    public void eat(){
        System.out.println("小猫爱吃鱼");
    }
    public void catchMouse(){
        System.out.println("小猫抓老鼠....");
    }
}

