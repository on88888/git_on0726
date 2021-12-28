package day12OOPAndException.src.com.atguigu.innerclass.in3;
/*
局部内部类：写在方法内的内部类局部内部类

    1.局部内部类 只有一个访问权限 default
    2. 不能有静态资源 可以静态的常量
    3.  局部内部类地位比较低 使用什么资源 是由所在方法决定
    4. 会生成独立的字节码文件  命名方式
        外部类名$序号内部类名.class  序号从1开始
          Outer$1A.class
          Outer$2A.class
     5.使用局部内部类资源 需要创建局部内部类对象
     6.如果局部内部类使用了 该类所在方法的局部变量 那么该变量会被final修饰

 */
public class Outer {
    String name ="outer";

    static int age = 20;

    public static void main(String[] args) {
    }
    public static void a(){
        int m = 10;

        class A{
          final   static int age = 30;
          public void show(){
            //  System.out.println(name);
              System.out.println(age);
          }
        }
        A a = new A();
        a.show();


    }
    public void b(){
     int m = 10;
      //  m = 20;
        class A{
            public void show(){
               /* System.out.println(name);
                System.out.println(age);*/

                System.out.println(m);
            }
        }
    }
}
