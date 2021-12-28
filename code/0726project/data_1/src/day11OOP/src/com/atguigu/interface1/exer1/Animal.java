package day11OOP.src.com.atguigu.interface1.exer1;

public class Animal implements LiveAble {
    @Override
    public void eat() {
        System.out.println("吃东西");
    }

    @Override
    public void breathe() {
        System.out.println("吸入氧气 呼出二氧化碳");
    }

    @Override
    public void sleep() {
        System.out.println("闭上眼睛睡觉");
    }
}
