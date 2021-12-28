package day11OOP.src.com.atguigu.poly.p2;

public class Person {
    String name = "Person";

    public void eat() {
        System.out.println("人吃饭");
    }
}

class Student extends Person {
    String name = "Student";

    @Override
    public void eat() {
        System.out.println("学生吃饭.....");
    }
}