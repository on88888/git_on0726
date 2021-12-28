package day13ExceptionAndAPI.src.com.atguigu.exception.e2;

public class ExceptionTest {
    //jvm
    public static void main(String[] args) {

        method1();
    }

    private static void method1() {

        method2();
    }

    private static void method2() {

        method3();
    }

    private static void method3() {

        int[] arr  ={10,20};

        System.out.println("arr[10] = " + arr[10]);
    }
}
