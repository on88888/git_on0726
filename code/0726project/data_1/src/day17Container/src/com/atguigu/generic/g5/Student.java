package day17Container.src.com.atguigu.generic.g5;

public class Student<T> {
    String name;
    T score;

    public Student(String name, T score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
