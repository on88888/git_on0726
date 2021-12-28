package day11OOP.src.com.atguigu.interface1.i4;
/*

1.java中接口可以多实现
2.如果实现多个接口 有同名的默认方法 那么 必须对重名方法重写
    重写的方法内 想要调用 接口中的默认方法 接口名.super.方法名
3.如果既继承一个类 又想要实现接口 先继承再实现

4.亲爹优先 继承一个类 又想要实现接口 有同名的方法 优先调用继承的

5.接口可以多继承
6.一个类实现接口 必须重写接口内所有的抽象方法 否则 此类要变为抽象类
 */
public class Test {
    public static void main(String[] args) {

        /*Person p = new Person();
        p.play();*/
        Student s= new Student();
        s.cc();
    }
}
