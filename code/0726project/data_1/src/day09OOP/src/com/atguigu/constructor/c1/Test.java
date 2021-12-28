package day09OOP.src.com.atguigu.constructor.c1;
/*
问题： 每次创建对象后 给属性赋值 对象名.属性名 =值  重复很多次

解决：使用构造器解决
一.声明构造器
    [修饰符] 类名(形参列表){

    }
二.注意：
    1.当类中没有构造器时  就会有一个默认的 隐藏的 无参的构造器
     一旦写了有参构造器 那么默认的 隐藏的 无参的构造器 自动消失
    2.构造器通过 new 关键字调用

    3.构造器的作用 快速给成员变量赋值

    4.区分局部变量 与成员变量使用this
    5.构造器支持重载的
 */
public class Test {
    public static void main(String[] args) {

        Student s3 = new Student("白居易", 29, 99, 1.79);

        s3.showInfo();



        Student s1 = new Student();
        //给属性赋值
        s1.name="李白";
        s1.height=1.87;
        s1.score=99;
        s1.age=20;
        s1.showInfo();

       /* Student s2 = new Student();
        s2.name="杜甫";
        s2.age=19;
        s2.score=88;
        s2.height=1.78;

        s2.showInfo();*/

    }
}
