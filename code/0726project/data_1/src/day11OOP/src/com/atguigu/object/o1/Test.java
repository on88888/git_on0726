package day11OOP.src.com.atguigu.object.o1;
/*
Object 类是所有类的根父类
    任意对象 都可以使用Object类中的方法
    数组也是对象 也可以使用
     除了Object 类之外 所有的类 都可以写出向上转型的关系

 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        //test.wait();
        int[] arr = {10,20,30};
        arr.hashCode();
        Object o = new Test();

    }
}
