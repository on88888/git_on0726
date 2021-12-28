package day17Container.src.com.atguigu.generic.g1;

public class Circle implements Comparable<Circle>{
    int r;

    public Circle(int r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                '}';
    }

    @Override
    public int compareTo(Circle o) {
        return this.r-o.r;
    }


  /*  public int compareTo(Object o) {
        Circle c = (Circle)o;
        return this.r-c.r;
    }*/
}
