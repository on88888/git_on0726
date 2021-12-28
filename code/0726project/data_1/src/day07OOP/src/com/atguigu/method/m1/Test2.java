package day07OOP.src.com.atguigu.method.m1;

/*
方法的声明/定义：
        修饰符    返回值类型   方法名  形参列表      方法的范围
    [public static] void      main ([String[] args]) {}

    ():方法的标识

注意：
    1.方法只声明不调用 不会执行
    2.方法谁先调用 谁先执行
    3.方法与方法之间是兄弟关系
    4.方法执行完毕会回到方法的调用处
 */
public class Test2 {
    //方法的声明
    public static void methodTest() {
        System.out.println("this is methodTest");
    }

    //jvm
    public static void main(String[] args) {
        System.out.println(666);
        show();
        methodTest();

    }
    //方法的声明
    public static void show() {
        System.out.println("this is show()");
    }

}
