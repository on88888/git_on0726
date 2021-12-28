package day16Container.src.com.atguigu.list.arraylist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
Collection
    -- List
            有序：（索引顺序）添加顺序
            不唯一: 元素可以重复



 */
public class ArrayListTest {
    @Test
    public void test04() {
        //多态
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("张三");

        /*
        获取集合内的范围数据
        subList(开始下标, 结束下标)
        [开始,结束)
         */
        List<String> subList = list.subList(0, 2);

        System.out.println("subList = " + subList);


        //获取指定下标的元素
        String s = list.get(1);

        System.out.println("s = " + s);

        /*
        indexOf()
            查找指定元素第一次出现的下标
            没有返回-1
         */
        int index = list.indexOf("张三1");

        System.out.println("index = " + index);

        /*
        lastIndexOf（）
         查找指定元素最后一次出现的下标
         没有返回-1
         */
        int lastIndexOf = list.lastIndexOf("张三1");

        System.out.println("lastIndexOf = " + lastIndexOf);


    }

    @Test
    public void test03() {
        //多态
        List<String> list = new ArrayList<>();

        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        list.add("张三");
        System.out.println("list = " + list);
        //修改指定下标的元素
        list.set(0, "李白");
        System.out.println("list = " + list);


    }


    @Test
    public void test02() {
        //多态
        List<String> list = new ArrayList<>();

        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        list.add("张三");
        System.out.println("list = " + list);
        //删除第一个符合要求的元素
        list.remove("张三");
        System.out.println("list = " + list);
        //删除指定下标的元素
        list.remove(2);
        System.out.println("list = " + list);


    }


    @Test
    public void test01() {
        //多态
        List<String> list = new ArrayList<>();

        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        list.add("张三");

        System.out.println("list = " + list);

        //向指定的位置添加指定的元素
        list.add(1, "王安石");

        System.out.println("list = " + list);//[张三, 王安石, 李四, 王五, 赵六, 张三]


        List<String> l1 = new ArrayList<>();
        l1.add("居里夫人");
        l1.add("祖冲之");
        l1.add("爱因斯坦");

        //将一个集合添加到指定的位置
        list.addAll(2, l1);
        System.out.println("list = " + list);// [张三, 王安石, 居里夫人, 祖冲之, 爱因斯坦, 李四, 王五, 赵六, 张三]


    }
}
