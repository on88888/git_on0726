package day19IOAndThread.src.com.atguigu.io.object;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -1075567002987632755L;
    String name;
    transient int age;
  static   double salary;
    char sex;
    double height;

    public Person(String name, int age, double salary, char sex) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.sex = sex;
    }


    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", sex=" + sex +
                ", height=" + height +
                '}';
    }
}
