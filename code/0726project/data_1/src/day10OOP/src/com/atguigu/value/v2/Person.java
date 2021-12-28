package day10OOP.src.com.atguigu.value.v2;

public class Person {

    String name;

    static int age=90;
    {
        System.out.println("普通代码块");
    }
    static {
        age = 30;
    //    name = "李白";
        System.out.println("静态代码块一");
        int n = 10;
    }

    public Person() {
        System.out.println("无参");
    }

    static {
        age = 30;
        //    name = "李白";
        System.out.println("静态代码块二");

    }


}
