package day11OOP.src.com.atguigu.native1;
/*
被native 修饰的方法 本地方法
     public native int hashCode();
    本地方法 没有方法体 但是可以直接使用
    本地方法调用时 会在本地方法栈内开辟空间
 */
public class Test {
    public static void main(String[] args) {

        Test test = new Test();

        int i = test.hashCode();
        System.out.println("i = " + i);

    }
}
