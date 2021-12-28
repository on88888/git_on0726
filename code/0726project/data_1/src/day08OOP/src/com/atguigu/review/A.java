package day08OOP.src.com.atguigu.review;
/*
 静态方法：在本类中可以直接调用
          不同类 需要使用 方法名调用

 */
public class A {

    public static void main(String[] args) {

        boolean zhiShu = Test.isZhiShu(9);

        System.out.println("zhiShu = " + zhiShu);

        System.out.println("Test.isZhiShu(10) = " + Test.isZhiShu(10));
    }
}
