package day10OOP.src.com.atguigu.init.classinit.c1;

public class Student {


    static {
        System.out.println("静态代码块一");
    }
    static String name ="Student";

    static int  age =18;
    static {
        System.out.println("静态代码块二");
    }

 /*   public static void main(String[] args) {
        System.out.println(666);
    }*/
}
