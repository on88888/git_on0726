package day12OOPAndException.src.com.atguigu.innerclass.in4.i2;
/*
匿名内部类：没有名字的内部类
        简化代码

   单创建完毕匿名内部类两个作用
        1.完成了该类匿名子类创建
        2.完成了该类匿名子类对象创建

如何创建匿名内部类：
    1. new 类(){};

    2.new 接口(){};

注意：
    1.匿名内部类又独立的字节码文件   命名方式
        外部类名$序号.class 序号从1开始
         Test$1.class
    2.匿名内部类是特殊的局部内部类
 */
public class Test {
    public static void main(String[] args) {
        new Fly(){
            @Override
            public void fly() {
                System.out.println("超人飞");
            }
        }.fly();

        int hashCode = new Object() {
            public void a() {
                System.out.println("this is a");
            }
            @Override
            public int hashCode() {
                return 10;
            }
        }.hashCode();
        System.out.println("hashCode = " + hashCode);


        new Person(){
            @Override
            public void show() {
                System.out.println("Student show()");
            }
            public void s(){
                System.out.println("this is Student s");
            }
        }.p();
    }
}
