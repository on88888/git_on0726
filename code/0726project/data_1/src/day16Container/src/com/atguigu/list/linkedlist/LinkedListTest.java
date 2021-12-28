package day16Container.src.com.atguigu.list.linkedlist;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/*
Collection
   -- List
       -- LinkedList

 */
public class LinkedListTest {

    @Test
    public void test01(){
        LinkedList<String> list = new LinkedList<>();

        list.add("张三");
        list.add("李四");
        list.add("王五");
        System.out.println("list = " + list);


        //因为LinkedList 底层是循环链表 新增了操作头尾的方法

        //新增头尾
        list.addFirst("李白");

        list.addLast("杜甫");
        System.out.println("list = " + list);
        //获取头尾
        System.out.println("list.getFirst() = " + list.getFirst());
        System.out.println("list.getLast() = " + list.getLast());
        //删除头尾
        list.removeFirst();
        list.removeLast();
        System.out.println("list = " + list);

    }
    @Test
    public void test02(){
        //将数据转为 集合存储
        //返回固定列表的集合 不能对元素进行 新增和 删除
        List<Integer> list = Arrays.asList(10, 20, 30, 40);

        System.out.println("list = " + list);
       // list.add(66);
        list.remove(1);
        System.out.println("list = " + list);

    }
}
