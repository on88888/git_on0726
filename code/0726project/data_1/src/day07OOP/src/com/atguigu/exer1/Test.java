package day07OOP.src.com.atguigu.exer1;

public class Test {
    public static void main(String[] args) {


        Circle c1 = new Circle();
        c1.r=10;
        double area1 = c1.r*c1.r*Math.PI;

        System.out.println("半径是："+ c1.r+"， 面积是 :"+ area1);

        //创建第二个圆
        Circle c2 = new Circle();
        c2.r=20;
        double area2 = c2.r * c2.r * Math.PI;
        System.out.println("半径是："+ c2.r+"， 面积是 :"+ area2);



    }
}
