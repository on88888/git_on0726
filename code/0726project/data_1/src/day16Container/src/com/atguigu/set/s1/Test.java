package day16Container.src.com.atguigu.set.s1;

import java.util.HashSet;
import java.util.Set;

/*
Collection
    -- List  有序 不唯一
       -- ArrayList
       -- LinkedList
       -- Vector
       -- Statck

    -- Set  元素唯一
       -- HashSet
            无序 ： 不是按照添加顺序展示
                    hash表内的顺序进行展示
            唯一： 数据不能重复

            注意： 1.存储自定义类型数据 必须 重写 hashCode() equals() 才能保证 无序唯一
                   2.存储自定义类型数据 有自定义属性 那么属性也需要重写   hashCode() equals() 才能保证 无序唯一


       -- LinekdHashSet
       -- TreeSet
       set:相较于 Collection 并没有新增方法
 */
public class Test {






    @org.junit.Test
    public void test03(){
        //创建set 对象
        HashSet<Person> set = new HashSet<>();

        //准备4个对象
        Person p1 = new Person("张三", 20);
        Person p2 = new Person("张三", 20);
        Person p3 = new Person("李四", 30);
        Person p4 = new Person("李四", 30);

        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);


        System.out.println("set.size() = " + set.size());


        System.out.println("set = " + set);


    }


    @org.junit.Test
    public void test02(){
        //多态
        Set<String> set = new HashSet<>();

        set.add("张三");
        set.add("李四");
        set.add("王五");
        set.add("赵六");
        set.add("王五");
        System.out.println(set);
    }


    @org.junit.Test
    public void test01(){

        Set<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(10);
        System.out.println(set);
        System.out.println("set.isEmpty() = " + set.isEmpty());

        System.out.println("set.size() = " + set.size());

        set.remove(20);

        System.out.println("set = " + set);
    }

}
