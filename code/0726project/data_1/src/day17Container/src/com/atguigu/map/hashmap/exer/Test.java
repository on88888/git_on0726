package day17Container.src.com.atguigu.map.hashmap.exer;

import java.util.*;

/*
  使用集合对象存储不同班级的学生

  学生类
        String name
        int age


  1班
     三个 学生

 2 班
    2个学生

 3 班
    3个学生




 */
public class Test {

    public static void main(String[] args) {
        //1.创建集合对象
        Map<Integer, ArrayList<Student>>map = new HashMap<>();

        //1.创建8个学生对象

        Student s1 = new Student("张三1", 20);
        Student s2= new Student("张三2", 20);
        Student s3 = new Student("张三3", 20);

        ArrayList<Student> one = new ArrayList<>();
        //将学生对象存到 集合内
        Collections.addAll(one, s1,s2,s3);

        map.put(1, one);

        Student s4 = new Student("张三4", 20);
        Student s5 = new Student("张三5", 20);
        ArrayList<Student> two = new ArrayList<>();

        Collections.addAll(two, s4,s5);

        map.put(2, two);

        Student s6 = new Student("张三6", 20);
        Student s7 = new Student("张三7", 20);
        Student s8 = new Student("张三8", 20);

        ArrayList<Student> three = new ArrayList<>();

        Collections.addAll(three, s6,s7,s8);

        map.put(3, three);


        Set<Map.Entry<Integer, ArrayList<Student>>> entries = map.entrySet();


        for (Map.Entry<Integer, ArrayList<Student>> entry : entries) {

            System.out.println(entry.getKey()+"--->" + entry.getValue());

        }

    }

}
