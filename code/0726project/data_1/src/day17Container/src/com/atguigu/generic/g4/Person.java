package day17Container.src.com.atguigu.generic.g4;

public class Person<T>{
    String name;
    T mate;

    public Person(String name, T mate) {
        this.name = name;
        this.mate = mate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", mate=" + mate +
                '}';
    }
}
