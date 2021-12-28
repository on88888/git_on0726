package day17Container.src.com.atguigu.review;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
新建一个Student类
    name age  score 三个属性

    测试类中创建 5个学生对象  存到集合内

    首先 按照分数排序  分数一致按照年龄排序

    使用迭代器遍历 展示

 */
public class Test1 {

    @Test
    public void test02() {
        //创建学生对象
        Student s1 = new Student("张三", 18, 99.6);
        Student s2 = new Student("李四", 19, 99.5);
        Student s3 = new Student("王五", 20, 99.9);
        Student s4 = new Student("赵六", 13, 99.5);
        Student s5 = new Student("马七", 16, 99.6);
        //将对象存到集合内  根据排序要求 选择 TreeSet
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {

                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return s1.age - s2.age;
            }
        };
        TreeSet<Student> treeSet = new TreeSet<>(comparator);
        treeSet.add(s1);
        treeSet.add(s2);
        treeSet.add(s3);
        treeSet.add(s4);
        treeSet.add(s5);

        //遍历展示
        Iterator<Student> iterator = treeSet.iterator();

        while (iterator.hasNext()) {

            Student student = iterator.next();

            System.out.println(student);


        }


    }

@Test
    public void test01() {
        //创建学生对象
        Student s1 = new Student("张三", 18, 99.6);
        Student s2 = new Student("李四", 19, 99.5);
        Student s3 = new Student("王五", 20, 99.9);
        Student s4 = new Student("赵六", 13, 99.5);
        Student s5 = new Student("马七", 16, 99.6);
        //将对象存到集合内  根据排序要求 选择 TreeSet
        TreeSet<Student> treeSet = new TreeSet<>();
        treeSet.add(s1);
        treeSet.add(s2);
        treeSet.add(s3);
        treeSet.add(s4);
        treeSet.add(s5);


        Iterator<Student> iterator = treeSet.iterator();

        while (iterator.hasNext()) {

            Student student = iterator.next();

            System.out.println(student);


        }
    }


}
