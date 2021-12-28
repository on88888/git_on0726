package day09OOP.src.com.atguigu.inheritance.exer;

public class Test {
    public static void main(String[] args) {
        Circle c1 = new Circle("圆形", 10);

        String info = c1.getInfo();

        System.out.println("info = " + info);

        Rectangle r = new Rectangle("矩形", 30, 10);
        System.out.println("r.getInfo() = " + r.getInfo());

    }
}
