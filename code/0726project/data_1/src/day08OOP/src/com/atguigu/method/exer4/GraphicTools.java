package day08OOP.src.com.atguigu.method.exer4;

public class GraphicTools {


    public static Circle  getMaxAreaOfCircle(Circle c1,Circle c2){

        double area1 = Math.PI * c1.r * c1.r;
        double area2 = Math.PI * c2.r * c2.r;

        return area1>area2?c1:c2;

    }
}
