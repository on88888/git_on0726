package day11OOP.src.com.atguigu.interface1.i1;

public class Bird implements Fly {
    @Override
    public void fly() {
        System.out.println("小鸟飞...");
    }
}
class SuperMan implements Fly{

    @Override
    public void fly() {

    }
}
