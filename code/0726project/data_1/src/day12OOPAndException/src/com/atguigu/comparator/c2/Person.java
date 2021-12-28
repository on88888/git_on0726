package day12OOPAndException.src.com.atguigu.comparator.c2;

public class Person  {
    String name;
    int age;
    double salary;



    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}