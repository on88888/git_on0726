package day10OOP.src.com.atguigu.poly.p2;
/*
多态向上转型 方法引用问题

   多态向上转型  可以使用什么方法
   编译看左边 运行看右边  相较于  "="
编译时：写完代码后 (javac)
        看=右边类型 有什么资源
运行时：程序跑起来 (java)
        当前类型重写父类的方法 以及从父类继承的方法
注意：
    1.多态向上转型 不能使用子类自己新增资源
 */
public class Test {
    public static void main(String[] args) {
        Animal an = new Dog();
        an.eat();
        an.sleep();

        Animal a = new Cat();
        a.sleep();
        a.eat();

    }


}
