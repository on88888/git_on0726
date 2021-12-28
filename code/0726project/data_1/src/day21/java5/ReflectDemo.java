package day21.java5;

public class ReflectDemo {
    static {
//        System.out.println("类加载了.......");
    }
    /*
（1）运行主方法所在的类，要先完成类初始化，再执行main方法
（2）第一次使用某个类型就是在new它的对象，此时这个类没有初始化的话，先完成类初始化再做实例初始化
（3）调用某个类的静态成员（类变量和类方法），此时这个类没有初始化的话，先完成类初始化
（4）子类初始化时，发现它的父类还没有初始化的话，那么先初始化父类
（5）通过反射操作某个类时，如果这个类没有初始化，也会导致该类先初始化
     */
    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println("hello");
//        new A();
        //System.out.println(A.age);
        Class clazz = Class.forName("com.atguigu.java5.A");//获取A类运行时类的对象--"方法区该类的那个模板对象"
    }
}

class SuperA{
    static {
        System.out.println("supera初始化");
    }
}
class A extends SuperA{
    static {
        System.out.println("a初始化");
    }
    public static int age = 10;
}
