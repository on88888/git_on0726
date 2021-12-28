package day12OOPAndException.src.com.atguigu.comparable.c3;

public class Cat implements Comparable {

    String name;
    int age;
    double height;

    public int compareTo(Object o) {
        //使用子类独有的属性
        Cat c = (Cat)o;

        return age-c.age;
       // return Double.compare(height, c.height);
    }

    public Cat(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }


}
