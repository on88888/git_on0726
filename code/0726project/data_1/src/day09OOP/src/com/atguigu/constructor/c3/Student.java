package day09OOP.src.com.atguigu.constructor.c3;

public class Student {


    private String name;
    private int age = 20;
    private double score;

    public Student() {
    }

    public Student(String n, int a, double s) {
        this.age = a;
        this.name = n;
        this.score = s;
    }

}
