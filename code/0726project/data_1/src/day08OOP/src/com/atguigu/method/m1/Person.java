package day08OOP.src.com.atguigu.method.m1;

public class Person {
    static String country = "中国";
    int age = 30;

    public static void staticMethod1(){
        System.out.println("this is STATIC staticMethod1");
    }
    public static void staticMethod2(){
        System.out.println(country);
        /*System.out.println(age);
        method1();*/
        System.out.println("this is STATIC staticMethod2");
    }

    public void method1(){
        System.out.println(age);
        System.out.println(country);
        staticMethod2();
        System.out.println("this is method1");
    }

    public void method2(){
        System.out.println("this is method2");
    }

}
