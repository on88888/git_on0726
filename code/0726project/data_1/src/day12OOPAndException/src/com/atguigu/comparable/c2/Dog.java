package day12OOPAndException.src.com.atguigu.comparable.c2;

public class Dog implements Comparable{

    String name;
    int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int compareTo(Object o) {
        Dog d = (Dog)o;
        return age-d.age;
    }
}
