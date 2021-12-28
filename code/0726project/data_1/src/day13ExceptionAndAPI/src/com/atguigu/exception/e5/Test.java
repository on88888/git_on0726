package day13ExceptionAndAPI.src.com.atguigu.exception.e5;
/*
如何自定义异常？
    1.新建一个类型 继承一个异常类型
            编译时异常  继承一个编译时异常类
            运行时异常  继承一个运行时异常类

    2.创建两个构造器
                一个无参
                一个String 参数
    注：如果抛出的是 编译时异常类型 哪里调用 哪里处理

 */
public class Test {
    public static void main(String[] args) {


        Student student = new Student();


        try {
            student.setSex('A');
        } catch (SexException e) {
           // e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        try {
            student.setAge(1000);
        } catch (AgeException e) {
            e.printStackTrace();
        }

        System.out.println(student);

    }
}
