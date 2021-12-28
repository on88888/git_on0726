package day11OOP.src.com.atguigu.poly.p1;

public class TestInstanceOf {
    public static void main(String[] args) {
        System.out.println("你好" instanceof String);
        System.out.println("你好" instanceof Object);

        System.out.println("-----------------");
        Dog d = new Dog();

        System.out.println(d instanceof Dog);
        System.out.println(d instanceof Animal);
        System.out.println(d instanceof Object);
        //System.out.println(d instanceof String);
        System.out.println("-----------------");
        Animal a = new Cat();

        System.out.println(a instanceof Cat);
        System.out.println(a instanceof Animal);
        System.out.println(a instanceof Dog);


    }
}
