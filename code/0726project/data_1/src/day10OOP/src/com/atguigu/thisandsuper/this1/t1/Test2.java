package day10OOP.src.com.atguigu.thisandsuper.this1.t1;
/*
this:代指当前对象
           当前对象:谁调用就是谁
       this可以调用 属性
                    方法
        this 可以调用本类中的构造器
              必须位于首行
              一般是多参调少参  偶尔少参调多参 常见于源码
注意：
    静态方法内不能使用this
 */
public class Test2 {
    public static void main(String[] args) {

        Student s1 = new Student("李白", 20);

        s1.showInfo();

        Student s2 = new Student();
        s2.showInfo();


    }
}
