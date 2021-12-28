package day12OOPAndException.src.com.atguigu.comparable.c2;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        Dog dog1 = new Dog("小白", 3);
        Dog dog2 = new Dog("小黑", 5);
        Dog dog3 = new Dog("小黄", 4);
        Dog dog4 = new Dog("小绿", 2);

       Dog[] ds = {dog1,dog2,dog3,dog4};

        for (Dog d : ds) {

            System.out.println("排序前 = " + d);
        }
        Arrays.sort(ds);
        System.out.println();
        for (Dog d : ds) {

            System.out.println("排序后 = " + d);
        }
    }
}
