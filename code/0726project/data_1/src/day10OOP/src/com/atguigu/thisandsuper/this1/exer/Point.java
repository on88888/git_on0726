package day10OOP.src.com.atguigu.thisandsuper.this1.exer;

public class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double getDistance(Point p1,Point p2){

        double tempDistance  = (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);

        double distance = Math.sqrt(tempDistance);

        return distance;
    }

    public double getDistance(Point p){
        System.out.println(this);

      //  double tempDistance  = (this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y);
        //this 在本类中可以省略
        double tempDistance  = (x-p.x)*(x-p.x)+(y-p.y)*(y-p.y);

        double distance = Math.sqrt(tempDistance);

        return distance;


    }


}
