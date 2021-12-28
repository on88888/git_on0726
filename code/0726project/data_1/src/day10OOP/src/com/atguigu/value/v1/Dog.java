package day10OOP.src.com.atguigu.value.v1;

public class Dog  {

    String name;
    static int age;
    {
        int m =10;
        System.out.println("代码块1");
    }

    public Dog(String name) {
        this.name = name;
        System.out.println("Dog 有参");
    }
    public Dog() {
        System.out.println("Dog 无参");
    }
    {
        age = 20;
        name = "大黄";
        //  System.out.println(m);
        System.out.println("代码块2");
    }
    public void show(){
        System.out.println(name+"  "+ age);
    }
}
