package day11OOP.src.com.atguigu.abstract1.a2;

public abstract class Animal {
    String name = "Animal";

    public Animal(String name) {
        this.name = name;
    }

    public Animal() {
    }

    public void show(){
        System.out.println("this is Animal show()");
    }
        abstract void sleep();

    abstract void eat();
}
abstract class Cat extends Animal{
}
 class Dog extends Animal{
    @Override
    void sleep() {
        System.out.println("小狗睡觉");
    }

     @Override
     void eat() {
         System.out.println("小狗吃肉");
     }
     // abstract void lookHome();
}


