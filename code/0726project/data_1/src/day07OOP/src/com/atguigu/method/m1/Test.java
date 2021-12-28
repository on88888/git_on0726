package day07OOP.src.com.atguigu.method.m1;

public class Test {
    public static void main(String[] args) {
        showHelloWorld(5);
        showHelloWorld(15);
    }
    public static void showHelloWorld(int count){
        for (int i = 0; i < count; i++) {
            System.out.println("HelloWorld\t"+(i+1));
        }
    }
}
