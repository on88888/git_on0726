package day07OOP.src.com.atguigu.packag;
/*
全路径名/全类名： 包名+类名
 */
import day07OOP.src.com.atguigu.oop.Student;
public class PackageTest2 {
    public static void main(String[] args) {

        Student s1 = new Student();
        System.out.println("s1.name = " + s1.name);

        day07OOP.src.com.atguigu.packag.Student s2 = new day07OOP.src.com.atguigu.packag.Student();

        System.out.println("s2.name = " + s2.name);

    }
}
