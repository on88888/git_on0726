package day16Container.src.com.atguigu.set.exer;

import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        //1.创建集合对象
        TreeSet<Circle> set = new TreeSet<>();
        //2.创建圆对象
        Circle c1 = new Circle(3);
        Circle c2 = new Circle(13);
        Circle c3 = new Circle(32);
        Circle c4 = new Circle(23);

        //3.添加数据

        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(c4);


        //4.遍历展示
        for (Circle circle : set) {
            System.out.println(circle);
        }




    }
}
