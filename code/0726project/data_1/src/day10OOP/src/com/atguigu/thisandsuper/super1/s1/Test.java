package day10OOP.src.com.atguigu.thisandsuper.super1.s1;
/*
super:从父类继承的资源
    super:可以调用父类的属性
                       方法
                       构造器
 注意：
    1.调用构造器时  this() super 只能出现一个
    2.静态方法内 不能使用super
 */
public class Test {
    public static void main(String[] args) {
        Dog d = new Dog("旺财", 3);
        d.show();//旺财

        Dog d1 = new Dog();
        d1.show();//Dog
    }
}
