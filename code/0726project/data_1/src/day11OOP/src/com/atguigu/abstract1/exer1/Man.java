package day11OOP.src.com.atguigu.abstract1.exer1;

public class Man extends Person {
    @Override
    public void walk() {
        System.out.println("大步流星走");
    }
    @Override
    public void eat() {
        System.out.println("狼吞虎咽吃");
    }
    public void smoke(){
        System.out.println("吞云吐雾.....");
    }
}
