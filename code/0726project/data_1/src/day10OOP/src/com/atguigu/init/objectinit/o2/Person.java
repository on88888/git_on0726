package day10OOP.src.com.atguigu.init.objectinit.o2;

public class Person {
    String name = getName();
    public String getName() {
        System.out.println("Person  getName()");
        return null;
    }
    {
        System.out.println("Person 成员代码块1");
    }

    public Person() {
        super();
        System.out.println("Person 无参");
    }
}
class Student extends Person{
    {
        System.out.println("Student 成员代码块1");
    }
    int age = getAge();

    private int getAge() {
        System.out.println("Student  getAge()");
        return 10;
    }
    public Student(){
        super();
        System.out.println("Student 无参");
    }
}
