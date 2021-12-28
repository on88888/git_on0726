package day08OOP.src.com.atguigu.method.exer3;

public class Test {

    public static void main(String[] args) {


        Rectangle r1 = new Rectangle();

        r1.width=5;
        r1.length=10;

        String info = r1.getInfo();


        System.out.println("info = " + info);
    }

}
