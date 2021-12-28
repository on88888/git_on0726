package day08OOP.src.com.atguigu.method.exer4;

public class Test {
    public static void main(String[] args) {

        //创建圆对象
        Circle c1 = new Circle();
        c1.r = 10;
        //创建圆对象
        Circle c2 = new Circle();
        c2.r=20;

        //比较两个圆对象
        Circle c = GraphicTools.getMaxAreaOfCircle(c1, c2);


        System.out.println(c.r);


    }
}
