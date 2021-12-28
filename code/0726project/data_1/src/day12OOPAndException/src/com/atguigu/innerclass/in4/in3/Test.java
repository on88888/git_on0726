package day12OOPAndException.src.com.atguigu.innerclass.in4.in3;

public class Test {
    public static void main(String[] args) {

        //多态
        Object o    = new Object() {
            //匿名子类对象重写Object类的资源
            @Override
            public int hashCode() {
                return 10;
            }
            //匿名子类对象自己独有资源
            public void a() {
                System.out.println("this is a()");
            }
        };

        int i = o.hashCode();
        System.out.println("i = " + i);


        //Animal a = new Dog();
        Animal a = new Animal() {
            @Override
            void eat() {
                System.out.println("小猫吃鱼");
            }
            void cc() {
                System.out.println("this is cc");
            }
        };
        a.eat();
        //a.cc();无法使用子类新增的资源


    }
}
