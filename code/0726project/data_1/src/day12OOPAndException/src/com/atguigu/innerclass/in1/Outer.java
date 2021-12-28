package day12OOPAndException.src.com.atguigu.innerclass.in1;

/*
成员内部类
    1.如何声明
        外部类名{
            class 内部类名{}
        }
    2.外部类只能被 public default 修饰
       成员内部类 是可以被所有访问权限修饰

    3.成员内部类 内部不能有静态的资源
       可以有静态的常量
    4.成员内部类 可以使用外部类中所有的资源

    5.成员内部类 可以打破单继承的限制

    6.如何在外部创建内部类对象
            外部类名.内部类名 对象名 = new 外部类名().new 内部类名()
            Outer.Inner inner1 = new Outer().new Inner();
    7.内部类会生成独立的字节码文件 命名方式
        外部类名$内部类名.class Outer$Inner.class

    8.每一个类中都有一个this
        使用外部类中的this 外部类名.this.资源名
 */
class C{
    void c(){}
}
class D{
    void d(){
    }
}
public class Outer extends C  {
     String name = "outer";
     static char s = 'A';

    public void outerMethod() {
        System.out.println("this is outerMethod()");
    }
    public void outerMethod2() {
        Inner inner = new Inner();
            inner.innerMethod2();
       /* inner.innerMethod();
        inner.d();*/
    }
    protected class Inner extends D {
        final static int age = 10;
        String name = "inner";
        public void innerMethod(){
            System.out.println(name);
            outerMethod();
            System.out.println(s);
        }
        public void innerMethod2(){
            String name = "innerMethod2";
            System.out.println("name = " + name);
            System.out.println("this.name = " + Inner.this.name);

            System.out.println("Outer.this.name = " + Outer.this.name);
            // System.out.println("this is innerMethod2()");
        }

    }


}
