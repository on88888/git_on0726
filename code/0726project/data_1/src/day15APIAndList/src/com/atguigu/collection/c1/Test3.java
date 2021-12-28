package day15APIAndList.src.com.atguigu.collection.c1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
集合的遍历
    1. 增强for

    2. 迭代器


 */



public class Test3 {

    @Test
    public void test04() {
        Collection<String> c = new ArrayList<>();
        c.add("张三");
        c.add("李四");
        c.add("王五");
        c.add("赵六");
        //将集合转为指定类型的数组

        String[] array = c.toArray(new String[]{});


    }


    @Test
    public void test03() {
        //泛型是Person
        Collection<Person> c = new ArrayList<>();

        c.add(new Person("张三", 20));
        c.add(new Person("李四", 30));
        c.add(new Person("王五", 40));

        Object[] objects = c.toArray();

        //将集合转为指定类型的数组
        Person[] s = c.toArray(new Person[]{});


    }

    @Test
    public void test02() {
        //泛型是Person
        Collection<Person> c = new ArrayList<>();

        c.add(new Person("张三", 20));
        c.add(new Person("李四", 30));
        c.add(new Person("王五", 40));

        Iterator<Person> iterator = c.iterator();

        while (iterator.hasNext()){
            Person person = iterator.next();
            System.out.println("person = " + person);
        }
    }

    @Test
    public void test01(){
        Collection<String> c = new ArrayList<>();
        c.add("张三");
        c.add("李四");
        c.add("王五");
        c.add("赵六");

        //获取迭代器对象
        Iterator<String> iterator = c.iterator();

        while (iterator.hasNext()){

            String ele = iterator.next();


            System.out.println("ele = " + ele);
        }
    }
}
