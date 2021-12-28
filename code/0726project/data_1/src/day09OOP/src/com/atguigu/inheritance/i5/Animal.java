package day09OOP.src.com.atguigu.inheritance.i5;
//super 调用父类的构造器
public class Animal {
    String name;

    public Animal() {
        System.out.println("Animal()");
    }

    public Animal(String name) {
        this.name = name;
    }
}

class Dog extends Animal {
    String color;

    public Dog() {
        super();
        System.out.println("Dog()");
    }
    public Dog(String color,String name){
        super(name);
        this.color=color;
    }

    public void show(){
        System.out.println(name+" ---> "+ color);
    }
}