package day10OOP.src.com.atguigu.thisandsuper.super1.s3;

public class Animal {
    String name = "Animal";


    public void show(){
        System.out.println("this is Animal show() name = "+ name);
    }
}

class Cat extends Animal{
    String name = "Cat";

   /* @Override
    public void show() {
      //  super.show();
        System.out.println("this is Cat show() name = "+name);
    }*/
}
