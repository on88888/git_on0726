package day11OOP.src.com.atguigu.abstract1.exer1;

public class Woman extends Person {


    @Override
    public void walk() {
        System.out.println("婀娜多姿走路");
    }

    @Override
    public void eat() {
        System.out.println("细嚼慢咽吃");
    }

    public void buy(){
        System.out.println("包治百病.....");
    }

}
