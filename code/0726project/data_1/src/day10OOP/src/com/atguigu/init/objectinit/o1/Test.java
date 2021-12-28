package day10OOP.src.com.atguigu.init.objectinit.o1;
/*
实例初始化：给普通成员变量赋值
    1.默认值
    2.显示赋值
    3.代码块赋值
    4.构造器内容

    1最前    2 3顺序看书写顺序      4最后 合并到 init()

注意：
    1.创建几次对象就会执行几次实例初始化

 */
public class Test {
    public static void main(String[] args) {

        Student s1 = new Student("王安石", 6);

        System.out.println("----------------------");
        Student s2 = new Student();
    }
}
