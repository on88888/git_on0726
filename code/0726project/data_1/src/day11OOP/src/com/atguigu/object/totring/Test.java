package day11OOP.src.com.atguigu.object.totring;
/*
如果单独输出对象的名字 会默认调用该对象的toString()
     com.atguigu.object.totring.Person@4554617c
重写toString() 要快速展示 属性信息
 */
public class Test {
    public static void main(String[] args) {

        Person p1 = new Person("张三", 20);

        System.out.println(p1);

        System.out.println("p1 = " + p1);

        String s = p1.toString();

        System.out.println("s = " + s);

    }
}
