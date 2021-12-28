package day16Container.src.com.atguigu.set.exer;
/*
容器：
    对数据增删改查
    遍历

 */
public class Circle implements Comparable {
    double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public int compareTo(Object o) {

        Circle c = (Circle)o;

        return Double.compare(this.r, c.r);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                '}';
    }
}
