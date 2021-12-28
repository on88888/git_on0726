package day09OOP.src.com.atguigu.inheritance.i3;

public class Animal {
    String name;
    int age;
    private String color;

    public void eat(){
        System.out.println("动物吃饭");
    }

}
class Cat extends Animal {
    public void catchMouse(){
        System.out.println("小猫抓老鼠");
    }
}


class Dog extends Animal{}


