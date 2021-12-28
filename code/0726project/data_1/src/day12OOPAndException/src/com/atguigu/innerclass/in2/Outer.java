package day12OOPAndException.src.com.atguigu.innerclass.in2;
/*
静态内部类：
    class 外部类名{
        static class 内部类名{
        }
    }
注意：
    1.静态内部类可以有静态的资源
    2.静态内部类 可以直接使用外部类静态资源
                如果是非静态资源 需要创建 外部类对象调用
    3.外部类引用内部类资源
            3.1静态资源可以通过 内部类名.资源名
            3.2非静态资源可以通过 内部类对象名.资源名
    4.在外部直接使用内部类资源
           4.1静态资源 外部类名.内部类名.资源名
           4.1非静态资源 先创建内部类对象
               外部类名.内部类名 对象名 = new 外部类名.内部类名();

    5.会生成独立的字节码文件  命名格式
            Outer$Inner.class



 */
public class Outer {
        String name = "Outer";
        static int age = 10;

        public void outerMethod(){
            Inner.innerMethod();
            Inner in = new Inner();
            System.out.println("in.name1 = " + in.name1);

        }
        public void outerMethod1(){
            Inner.innerMethod();
            Inner in = new Inner();
            in.innerMethod2();

        }

    static class Inner{
       String name1 = "Inner";
        static int age = 50;
        public static void innerMethod(){
            //System.out.println(age);
            int age = 90;
            System.out.println("age = " + age);
            System.out.println("Inner.age = " + Inner.age);
            System.out.println("Outer.age = " + Outer.age);

            System.out.println("this is static innerMethod()");
        }
        public void innerMethod2(){
          /*  Outer outer = new Outer();
            System.out.println(outer.name);*/
            System.out.println("this is static innerMethod2()");
        }


    }
}
