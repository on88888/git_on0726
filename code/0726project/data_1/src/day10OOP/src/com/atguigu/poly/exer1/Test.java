package day10OOP.src.com.atguigu.poly.exer1;

public class Test {
    public static void main(String[] args) {

        Traffic[] ts = {new Car(),new Bicyle()};

        for (Traffic t : ts) {

            t.drive();
        }

    }
}
