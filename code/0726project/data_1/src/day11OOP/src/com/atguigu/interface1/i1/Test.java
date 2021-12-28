package day11OOP.src.com.atguigu.interface1.i1;
/*
接口： 用来定义规范  has a的关系

声明接口： interface 接口名{}

使用接口： class 类名  implements 接口名{
}

注意：
    1.如果一个类实现了接口 那么 此类需要实现接口内所有的抽象方法
                          否则 此类要变为抽象类

    2.接口不能直接创建对象

    3.如果一个类实现了接口 可以把此类看成是该接口的孩子
 */
public class Test {
    public static void main(String[] args) {

        Fly f = new Bird();

        f.fly();
        System.out.println("-------------");

        Bird b = new Bird();

        b.fly();

        SuperMan s = new SuperMan();
        s.fly();


       // Fly f = new Fly();




    }
}
