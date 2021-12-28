package day12OOPAndException.src.com.atguigu.innerclass.in4.i2;

public interface Fly {
    void fly();
}
class Bird implements Fly{

    @Override
    public void fly() {
        System.out.println("小鸟飞");
    }
}