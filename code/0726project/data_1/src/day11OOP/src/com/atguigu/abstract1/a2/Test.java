package day11OOP.src.com.atguigu.abstract1.a2;
/*
注意：
    1.抽象类中 可以存在普通的方法  普通的属性
    2.抽象类中可以没有抽象方法
    3.抽象方法必须存在于抽象类中
    4.一个类继承了抽象了 那么此类要么实现所有的抽象方法
                               要么 此类也要变为抽象类
    5.抽象方法不能被 静态  私有  final修饰

 */
public class Test {
    public static void main(String[] args) {

        Animal a = new Dog();

        a.show();
        a.eat();



    }
}
