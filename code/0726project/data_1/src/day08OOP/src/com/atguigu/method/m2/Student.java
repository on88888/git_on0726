package day08OOP.src.com.atguigu.method.m2;
/*
普通成员变量 vs 局部变量区别？
    一.代码位置不同
            成员变量  类中方法外
            局部变量 方法内
    二.有无默认值
            成员变量 有默认值
            局部变量 没有默认值
     三.作用域不同
            成员变量 当前类的方法都有效
            局部变量 方法内有效
      四.内存位置不同
            成员变量 在堆中
            局部变量 在栈内
      五.生命周期不同
            5.1开始
                成员变量 创建对象开辟空间
                局部变量 方法调用
            5.2结束
                 成员变量  当没有引用指向堆中地址时等待 垃圾回收器回收
                 局部变量 方法执行挖完毕 就会弹栈  消失


 */
public class Student {
    String name ;
    public void  show(){
        System.out.println(name);
       // System.out.println(a);
    }
    //形参是该方法的局部变量
    public void sum(int n,int m){
        System.out.println(n+m);
        System.out.println(name);
    }
    public void add(){
           int a = 10;
        int b;


        //System.out.println("b = " + b);
        System.out.println(name);
    }
}
