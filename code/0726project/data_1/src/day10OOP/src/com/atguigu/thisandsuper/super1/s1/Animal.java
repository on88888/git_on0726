package day10OOP.src.com.atguigu.thisandsuper.super1.s1;


public class Animal {
    String name = "Animal";
    public Animal(String name) {
        this.name = name;
    }
    public Animal() {
    }

    public String showInfo(){
        return "name = "+name;
    }
}
class Dog extends Animal{
    int age = 3;
    String name = "Dog";
    public Dog() {
    }

    public Dog(int age) {
        this.age = age;
    }

    public Dog(String name, int age) {
        super(name);
       this.age = age;
       // this(age);

    }
    public void show(){
        System.out.println("name ="+ name+",super.name = "+super.name);
    }

    public static void cc(){

     //   System.out.println(super.name);
    }


    public String showInfo(){
        return super.showInfo()+"age = "+ age+"name = "+ name;
    }
}
