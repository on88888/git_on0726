package day17Container.src.com.atguigu.generic.g5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
/*
? super T 下限
？ super Dog
        最小是Dog
 */
public class TestSuper {
    Dog[] ds;

    @After
    public void test01(){
        for (Dog d : ds) {
            System.out.println("排序后   = " + d);
        }
    }

    @Before
    public void test00(){
        Dog d1 = new Dog("旺财", 10, 8.9);
        Dog d2 = new Dog("小黑", 11, 8.1);
        Dog d3 = new Dog("小白", 13, 8.3);
        Dog d4 = new Dog("小黄", 9, 8.7);
        ds = new Dog[]{d1,d2,d3,d4};

        for (Dog d : ds) {

            System.out.println("排序前   = " + d);
        }
        System.out.println();
    }
    @Test
    public void test02(){


        Comparator<Dog> c = new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return Double.compare(o1.weight, o2.weight);
            }
        };
        Arrays.sort(ds,c);

    }

    @Test
    public void test03(){



        Comparator<Animal> c = new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.age - o2.age;
            }
        };
        Arrays.sort(ds,c);
    }
}
