package day10OOP.src.com.atguigu.thisandsuper.super1.s2;

public class Animal {
    String name = "Animal";
}

class Dog extends Animal{
  //  String name = "Dog";
    public void show(){
       String name  ="show()";
        System.out.println("name = " + name);

        System.out.println("this.name = " + this.name);

        System.out.println("super.name = " + super.name);


    }



}

