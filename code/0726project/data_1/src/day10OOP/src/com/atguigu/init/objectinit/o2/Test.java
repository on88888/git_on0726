package day10OOP.src.com.atguigu.init.objectinit.o2;
/*
实例初始化：给普通成员变量赋值
    1.默认值
    2.显示赋值
    3.代码块赋值
    4.构造器内容

    1最前    2 3顺序看书写顺序      4最后 合并到 init()
注意：
    1.创建几次对象就会执行几次实例初始化
    2.存在继承关系 同时具有默认值 然后 先进行父类的实例初始化 再进行子类的实例初始化
    3.有继承关系 父类和子类有单独的init()
 */
public class Test {
    public static void main(String[] args) {

        new Student();
        System.out.println("---------------------");
        new Student();
    }
}
