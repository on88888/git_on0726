package day08OOP.src.com.atguigu.overload;
/*
方法重载：使用相同的方法名 表示不同的方法
        sum1()
        sum2()
        sum3()
        .......
 */
public class Test {
    public static void main(String[] args) {
        sum1(10,20);

        sum2(10,3.14);

        sum3(10,20,30);
    }

    private static void sum3(int i, int i1, int i2) {

        System.out.println("int + int + int ");
    }

    private static void sum2(int i, double v) {
        System.out.println("int + double");
    }


    public static void sum1(int i, int i1) {

        System.out.println("int + int");
    }
}
