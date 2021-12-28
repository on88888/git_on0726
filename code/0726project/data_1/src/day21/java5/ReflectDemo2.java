package day21.java5;

public class ReflectDemo2 {
    /*
        2、哪些使用类的操作，但是不会导致类的初始化？
（1）使用某个类的静态的常量（static  final）
（2）通过子类调用父类的静态变量，静态方法，只会导致父类初始化，不会导致子类初始化，
        即只有声明静态成员的类才会初始化
（3）用某个类型声明数组并创建数组对象时，不会导致这个类初始化
     */
    public static void main(String[] args) {
//        System.out.println(C.name);
        C[] cc = new C[10];
    }
}

class SuperC{
    static{
        System.out.println("superC初始化");
    }
    public static String name = "c";
}

class C extends SuperC{
    static{
        System.out.println("C初始化");
    }
    public static final int AGE=10;
}
