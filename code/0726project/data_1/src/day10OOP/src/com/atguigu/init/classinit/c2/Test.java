package day10OOP.src.com.atguigu.init.classinit.c2;
/*
类的初始化：
      1.类中静态成员变量显示赋值语句
      2.静态代码块内容
      会将上面两个内容合并到 <clinit>
触发类的初始化操作：
    1.在本类中执行main()
    2.使用了本类中的静态资源

注意：
    1.类的初始化顺序 先声明谁 就先合并谁
    2.类的初始化只会执行一次
    3.类的初始化只会执行一次
    4.类的初始化滞后
        子类使用了从父类继承的静态资源 只会导致父类初始化 不会导致子类初始化

 */
public class Test {
    public static void main(String[] args) {

        Student.cc();

        /*Student.show();
        System.out.println("-------------");
        Student.show();*/
    }
}
