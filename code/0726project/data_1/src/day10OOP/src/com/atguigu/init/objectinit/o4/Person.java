package day10OOP.src.com.atguigu.init.objectinit.o4;

public class Person {
    static String name = getName();
    public static String getName() {
        System.out.println("Person static getName() 1");
        return "";
    }
    static {
        System.out.println("Person static 代码块 2");
    }
    {
        System.out.println("Person 普通代码块 3");
    }
    int age =this. getAge();
    public int getAge() {
        System.out.println("Person getAge() 4");
        return 10;
    }
    public Person() {
        System.out.println("Person 无参 5");
    }
}
