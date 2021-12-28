package day16Container.src.com.atguigu.set.s3;

public class Student implements Comparable{


    String name;

    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Student s = (Student)o;
        return this.age-s.age;
    }
}
