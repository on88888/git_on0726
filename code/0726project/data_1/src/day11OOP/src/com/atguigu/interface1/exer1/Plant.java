package day11OOP.src.com.atguigu.interface1.exer1;

public class Plant  implements LiveAble{


    @Override
    public void eat() {
        System.out.println("吸收营养");
    }

    @Override
    public void breathe() {
        System.out.println("吸入二氧化碳 呼出氧气");
    }
}
