package day10OOP.src.com.atguigu.thisandsuper.this1.t1;

import javax.sound.midi.SoundbankResource;

public class Student {


    String name;
    int age;

    public void showInfo(){
        System.out.println("name = "+name+", age = "+age);

    }

    public Student() {
        this("李四",30);
    }

    public Student(String name, int age) {
   //     System.out.println(this);
       // this.name = name;
        this(name);
        this.age = age;
    }

    public Student(String name){
        this.name=name;
    }






    public void show(){

        System.out.println("this = " + this);
        System.out.println(this.name);

        this.test1();
    }

    public void test1(){

        System.out.println("age = "+ this.age);
    }

    public static void cc(){

    }
}
