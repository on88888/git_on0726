package day11OOP.src.com.atguigu.final1;

/*
 final修饰的类 不能被继承  不能有子类  final class 类名

final 修饰的方法 不能被重写  public final void eat(){}

final 修饰的变量 变为 常量
      修饰的对象  地址值不能改变

      修饰的成员变量  可以显示赋值 也可以通过构造器赋值

 */
public class Test {
    public static void main(String[] args) {

        final int m = 10;
        //   m = 20;
        System.out.println(m);

        final Dog d = new Dog("小白");
        d.name = "旺财";

    //    d = new Dog("小黑");

        d.show();

    }
}
