package day17Container.src.com.atguigu.generic.g4;

public class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Ghost{
    String name;

    public Ghost(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ghost{" +
                "name='" + name + '\'' +
                '}';
    }
}
class Robot{
    String name;

    public Robot(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "name='" + name + '\'' +
                '}';
    }
}
