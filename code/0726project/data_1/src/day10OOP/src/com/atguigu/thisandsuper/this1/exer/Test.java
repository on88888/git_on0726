package day10OOP.src.com.atguigu.thisandsuper.this1.exer;

public class Test {
    public static void main(String[] args) {

        Point p1 = new Point(10, 20);
        Point p2 = new Point(5, 10);

        double distance = Point.getDistance(p1, p2);
        System.out.println("distance = " + distance);

        double distance1 = p1.getDistance(p2);

        System.out.println("distance1 = " + distance1);
    }
}
