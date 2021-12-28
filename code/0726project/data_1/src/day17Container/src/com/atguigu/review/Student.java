package day17Container.src.com.atguigu.review;

public class Student implements Comparable {

    String name;
    int age;
    double score;

    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Student s = (Student)o;
        if(Double.compare(this.score, s.score)==0){

            return this.age-s.age;
        }

        return Double.compare(this.score, s.score);
    }
}
