package day08OOP.src.com.atguigu.method.m1;
/*
方法的分类：
        静态方法： 有 static 修饰的方法
                    使用 类名调用
                    在本类中可以省略类名 直接调用

        非静态方法： 没有  static 修饰的方法
                   必须使用对象 调用
                   在本类中可以省略对象 直接调用



 */
public class Test {
    public static void main(String[] args) {
        //创建对象
        Student s1 = new Student();

       /* s1.show("张三");

        int sum = s1.getSum(10, 20);

        System.out.println("sum = " + sum);*/
       s1.test();

    }
}
