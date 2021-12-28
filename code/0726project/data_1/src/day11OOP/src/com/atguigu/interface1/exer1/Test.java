package day11OOP.src.com.atguigu.interface1.exer1;

public class Test {
    public static void main(String[] args) {

        Animal a = new Animal();
        a.breathe();
        a.eat();
        a.sleep();
        System.out.println("------------------------");

        Plant p = new Plant();
        p.breathe();
        p.eat();
        p.sleep();
        LiveAble.drink();
    }
}
