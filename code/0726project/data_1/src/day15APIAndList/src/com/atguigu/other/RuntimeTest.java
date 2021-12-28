package day15APIAndList.src.com.atguigu.other;

import org.junit.Test;
/*
单例： 在程序执行过程只会有一个实例对象产生


 */

class Person{

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class RuntimeTest {

    @Test
    public void test03(){
        Runtime r = Runtime.getRuntime();

        System.out.println("总内存 = " + r.totalMemory());
        System.out.println("剩余内存 = " + r.freeMemory());

        for (int i = 0; i < 10_0000; i++) {

            new Person("张三"+i,i);
        }

        //运行垃圾回收器
        System.gc();

        System.out.println("总内存 = " + r.totalMemory());
        System.out.println("剩余内存 = " + r.freeMemory());
    }


    @Test
    public void test02(){

        Runtime r1 = Runtime.getRuntime();
        //获取总内存
        long totalMemory = r1.totalMemory();

        System.out.println("totalMemory = " + totalMemory);
        //获取剩余内存
        long freeMemory = r1.freeMemory();
        System.out.println("freeMemory = " + freeMemory);
    }
    @Test
    public void test01(){

        Runtime r1 = Runtime.getRuntime();
        Runtime r2 = Runtime.getRuntime();
        //运行时Runtime 是单例的

        System.out.println(r1==r2);
    }
}
