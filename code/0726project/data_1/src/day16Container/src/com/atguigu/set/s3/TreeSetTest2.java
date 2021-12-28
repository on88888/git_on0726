package day16Container.src.com.atguigu.set.s3;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest2 {

    @Test
    public void test03(){

        //创建比较规则对象  首先按照年龄比  年龄相等  按照薪水比较
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {

                if(o1.age==o2.age){
                    return Double.compare(o1.salary, o2.salary);
                }

                return o1.age-o2.age;
            }
        };
        //创建集合对象
        TreeSet<Person> set = new TreeSet<>(comparator);
        //创建Person对象

        Person p1 = new Person("张三", 20, 9999.9);
        Person p2 = new Person("李四", 22, 9999.1);
        Person p3 = new Person("王五", 20, 9999.3);
        Person p4 = new Person("赵六", 32, 9999.7);
        Person p5 = new Person("赵六6", 32, 9999.6);
        //添加数据
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        set.add(p5);

        //展示结果

        for (Person person : set) {
            System.out.println("person = " + person);
        }



    }
    @Test
    public void test02(){

        //创建比较规则对象
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return -Double.compare(o1.salary, o2.salary);
            }
        };
        //创建集合对象
        TreeSet<Person> set = new TreeSet<>(comparator);
        //创建Person对象

        Person p1 = new Person("张三", 20, 9999.9);
        Person p2 = new Person("李四", 22, 9999.1);
        Person p3 = new Person("王五", 20, 9999.3);
        Person p4 = new Person("赵六", 32, 9999.7);
        Person p5 = new Person("赵六6", 32, 9999.6);
        //添加数据
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        set.add(p5);

        //展示结果

        for (Person person : set) {
            System.out.println("person = " + person);
        }



    }


    @Test
    public void test01(){

        //创建对象
        TreeSet<Student> set = new TreeSet<>();

        //创建学生对象
        Student s1 = new Student("张三", 20);
        Student s2 = new Student("李四", 30);
        Student s3 = new Student("王五", 10);
        Student s4 = new Student("赵六", 40);
        Student s5 = new Student("安琪拉", 40);

        //添加数据
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);
        set.add(s5);

        System.out.println("set.size() = " + set.size());


        for (Student student : set) {
            System.out.println(student);
        }
    }
}
