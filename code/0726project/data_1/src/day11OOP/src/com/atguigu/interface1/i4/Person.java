package day11OOP.src.com.atguigu.interface1.i4;

public class Person implements A,B {

    @Override
    public void play() {
        System.out.println("自己玩......");
        A.super.play();
        B.super.play();
    }
    @Override
    public void a() {
    }
    @Override
    public void b() {
    }
}
interface A{
    void a();
    public default void play(){
        System.out.println("杨幂 出来玩.....");
    }
}
interface B{
    void b();
    public default void play(){
        System.out.println("迪丽热巴  出来玩 ......");
    }
}