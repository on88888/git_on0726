package day17Container.src.com.atguigu.map.hashmap;

import org.junit.Test;

import java.util.HashMap;
/*
HashMap  LinkedHashMap  key 存储自定义类型数据  需要重写 hashCode equals()

TreeMap key 存储自定义类型数据  需要指定比较规则

 */
public class HashMapTest2 {

    @Test
    public void test01(){

        Student s1 = new Student("张三", 20);
        Student s2 = new Student("李四", 22);

        HashMap<Student,String> map = new HashMap<>();
        //添加数据
        map.put(s1, "张三");
        map.put(s1, "李四");

        System.out.println("map.size() = " + map.size());
        System.out.println("map = " + map);


    }
}
