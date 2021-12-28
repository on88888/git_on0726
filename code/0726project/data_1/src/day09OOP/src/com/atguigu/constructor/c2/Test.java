package day09OOP.src.com.atguigu.constructor.c2;

public class Test {
    public static void main(String[] args) {

        Person p2 = new Person("张三", 200);

        p2.showInfo();

        Person p1 = new Person();

        p1.setName("李白");

        p1.setAge(20);

        p1.showInfo();

    }
}
