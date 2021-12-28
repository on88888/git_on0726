package day16Container.src.com.atguigu.set.s1;

import org.junit.Test;

import java.util.HashSet;

public class Test2 {

    @Test
    public void test01(){

        HashSet<Student> set = new HashSet<>();


        Student s1 = new Student("张三", new MyDate(2000, 3, 4));
        Student s2 = new Student("张三", new MyDate(2000, 3, 4));
        Student s3 = new Student("李四", new MyDate(2000, 3, 4));

        set.add(s1);
        set.add(s2);
        set.add(s3);


        System.out.println("set.size() = " + set.size());

        System.out.println("set = " + set);


    }
}
