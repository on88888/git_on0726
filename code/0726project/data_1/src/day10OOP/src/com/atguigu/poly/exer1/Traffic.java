package day10OOP.src.com.atguigu.poly.exer1;

public class Traffic {
    public void drive(){
    }
}

class Car extends Traffic{

    @Override
    public void drive() {
        System.out.println("汽车跑");
    }
}
class Bicyle extends Traffic{
    @Override
    public void drive() {
        System.out.println("自行车跑");
    }
}
