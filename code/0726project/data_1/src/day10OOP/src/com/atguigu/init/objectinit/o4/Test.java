package day10OOP.src.com.atguigu.init.objectinit.o4;
/*
先进行类的初始化 再进行实例初始化
静态方法没有重写
类的初始化只会执行一次
 */
public class Test {
    public static void main(String[] args) {
        new Student();
        System.out.println("------");

        new Student();

   /*
   126739589 10

    */


    }
}
