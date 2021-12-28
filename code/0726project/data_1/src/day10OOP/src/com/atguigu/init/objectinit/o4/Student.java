package day10OOP.src.com.atguigu.init.objectinit.o4;

public class Student  extends Person{
    static String name = getName();
    public static String getName() {
        System.out.println("Student static getName() 6");
        return "";
    }
    static {
        System.out.println("Student static 代码块 7");
    }
    {
        System.out.println("Student 普通代码块 8");
    }
    int age = getAge();
    public int getAge() {
        System.out.println("Person getAge() 9");
        return 10;
    }
    public Student() {
        super();
        System.out.println("Studnet 无参  10");
    }
}
