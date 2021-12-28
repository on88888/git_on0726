package day10OOP.src.com.atguigu.init.objectinit.o1;

public class Student {
    {
        System.out.println("普通代码块一");
    }
    String name = "李白";


    int age = 20;
    {
        System.out.println("普通代码块2");
    }

    public Student(String name1, int age1) {
        this.name = name1;
        this.age = age1;
    }

    public Student() {
        System.out.println("无参");
    }
}
