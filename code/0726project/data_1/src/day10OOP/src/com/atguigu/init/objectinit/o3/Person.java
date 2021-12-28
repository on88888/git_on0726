package day10OOP.src.com.atguigu.init.objectinit.o3;

public class Person {
    String name =this. getName();
    public String getName() {
        System.out.println("Person getName() 1");
        return null;
    }
    {
        System.out.println("Person 代码块 2");
    }

    public Person() {
        super();
        System.out.println("Person 无参 3");
    }
}

