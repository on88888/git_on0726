package day07OOP.src.com.atguigu.method.m3;
/*
方法的声明/定义：
        修饰符    返回值类型   方法名  形参列表      方法的范围
    [public static] void      main ([String[] args]) {}
    ():方法的标识

void: 代表方法没有返回值
非void： 方法有返回值的  必须返回一个该类型的值
  int
  double

 */
public class Test {
    public static void main(String[] args) {

        a();
        //int b接收方法的执行结果
        int b = b();
        System.out.println("b = " + b);
        //直接输出方法的执行结果
        System.out.println("b() = " + b());

    }

    public static int b(){
        return 10;
    }

    public static void a(){
        System.out.println("666");
    }

}
