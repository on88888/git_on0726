package com.atguigu.hive.test;

/**
 * @Author 0726
 * @ClassName HelloWorld
 * @createTime 2021年11月23日 18:23
 */
public class HelloWorld {
    public static void main(String[] args) {

        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hahaha world");

    }
}
