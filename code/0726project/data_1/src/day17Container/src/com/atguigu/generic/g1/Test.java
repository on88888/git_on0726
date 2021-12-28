package day17Container.src.com.atguigu.generic.g1;

import java.util.ArrayList;
import java.util.Comparator;

/*

泛型：类型参数

简化代码的操作
 */
public class Test {
    public static void main(String[] args) {

        Circle c1 = new Circle(10);
        Circle c2 = new Circle(20);

        ArrayList l;
        new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

        new Comparator<Circle>() {
            @Override
            public int compare(Circle o1, Circle o2) {
                return 0;
            }


        };

    }
}

