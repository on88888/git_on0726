package day11OOP.src.com.atguigu.abstract1.exer1;

public class Test {
    public static void main(String[] args) {
        //多态向上转型
        Person p1 = new Man();
        p1.walk();
        p1.eat();
        //多态向下转型
        Man m  =(Man)p1;
        m.smoke();

        Woman woman = new Woman();
        woman.eat();
        woman.walk();
        woman.buy();

    }
}
