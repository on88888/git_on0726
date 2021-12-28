package day09OOP.src.com.atguigu.inheritance.i2;

public class Animal {

    String name;
    int age;
    public void  eat(){
        System.out.println("吃饭");
    }

}

class Cat extends Animal{
    public void catchMouse(){
        System.out.println("小猫抓老鼠");
    }
}

class Dog extends Animal{
    public void lookHome(){
        System.out.println("小狗看家");
    }
}
