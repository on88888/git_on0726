package day10OOP.src.com.atguigu.review;
/*
1、声明父类：Person类
    包含属性：姓名，年龄，性别
    属性私有化，get/set
    包含getInfo()方法 返回信息：例如：姓名：张三，年龄：23，性别：男

2、声明子类：Student类，继承Person类
    新增属性：score成绩
    属性私有化，get/set
    包含getInfo()方法 返回信息：例如：姓名：张三，年龄：23，性别：男，成绩：89

3、声明子类：Teacher类，继承Person类
    新增属性：salary薪资
    属性私有化，get/set
    包含getInfo()方法 返回信息：例如：姓名：张三，年龄：23，性别：男，薪资：10000
4.新建学生和老师对象 给属性赋值 并测试
 */
public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge(20);
        s1.setSex('男');
        s1.setScore(99.8);

        System.out.println("s1.getInfo() = " + s1.getInfo());
        Teacher teacher = new Teacher();
        teacher.setSalary(9999);
        teacher.setAge(30);
        teacher.setName("李四");
        teacher.setSex('男');

        System.out.println("teacher.getInfo() = " + teacher.getInfo());

    }
}
