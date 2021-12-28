package day16Container.src.com.atguigu.set.s2;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/*
Collection
    -- List  有序 不唯一
       -- ArrayList
       -- LinkedList
       -- Vector
       -- Statck

    -- Set  元素唯一
       -- HashSet  哈西表
            无序 ： 不是按照添加顺序展示
                    hash表内的顺序进行展示
            唯一： 数据不能重复

            注意： 1.存储自定义类型数据 必须 重写 hashCode() equals() 才能保证 无序唯一
                   2.存储自定义类型数据 有自定义属性 那么属性也需要重写   hashCode() equals() 才能保证 无序唯一

       -- LinekdHashSet  哈西表
            有序：添加顺序
            唯一：数据不能重复
            注意： 1.存储自定义类型数据 有自定义属性 那么属性也需要重写   hashCode() equals() 才能保证 无序唯一
                   2. LinekdHashSet 有序：底层有对象的前一个值和后一个值的地址引用
       -- TreeSet

       set:相较于 Collection 并没有新增方法
 */
public class Test {
    @org.junit.Test
    public void test03(){
        LinkedHashSet<Cat> set = new LinkedHashSet<>();
        //四个对象
        Cat c1 = new Cat("旺财", 3);
        Cat c2 = new Cat("招财猫", 3);
        Cat c3 = new Cat("旺财", 3);
        Cat c4 = new Cat("招财猫", 3);

        //添加
        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(c4);
        System.out.println("set.size() = " + set.size());

        //遍历
        Iterator<Cat> iterator = set.iterator();

        while (iterator.hasNext()){

            Cat ele = iterator.next();

            System.out.println("ele = " + ele);
        }


    }

    @org.junit.Test
    public void test02(){
        //创建集合对象
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        //添加元素
        linkedHashSet.add("王安石");
        linkedHashSet.add("李商隐");

        linkedHashSet.add("李白");
        linkedHashSet.add("杜甫");
        linkedHashSet.add("杜甫");

        System.out.println("linkedHashSet.size() = " + linkedHashSet.size());

        //遍历
        Iterator<String> iterator = linkedHashSet.iterator();

        while (iterator.hasNext()){

            String ele = iterator.next();
            System.out.println("ele = " + ele);

        }


    }


    @org.junit.Test
    public void test01(){

        //多态
        Set<Integer> set = new LinkedHashSet<>();

        //添加数据
        set.add(60);
        set.add(10);

        set.add(20);

        set.add(30);
        set.add(30);

        for (Integer i : set) {
            System.out.println("i = " + i);

        }


    }
}
