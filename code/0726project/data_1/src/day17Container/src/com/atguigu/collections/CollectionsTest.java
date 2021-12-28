package day17Container.src.com.atguigu.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Collections 是操作数组的工具类
 */
public class CollectionsTest {
    @Test
    public void test05() {

        ArrayList<String> list = new ArrayList<>();

        list.add("李白");
        list.add("王安石");

        System.out.println(list);

        //将集合变为不可变视图 不能对元素 新增和删除
        List<String> list1 = Collections.unmodifiableList(list);

        list1.add("李商隐");
        System.out.println(list1);

    }


    @Test
    public void test04() {

        ArrayList<String> list = new ArrayList<>();

        list.add("李白");
        list.add("王安石");
        //将线程不安全的集合类变为线程安全的集合类
        Collections.synchronizedList(list);


    }



    @Test
    public void test03() {

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张三","李四","张三","王安石","王五");
        System.out.println("list = " + list);
        //反转
        Collections.reverse(list);
        System.out.println("list = " + list);

        //将旧的元素替换为新的元素
        Collections.replaceAll(list, "张三", "安琪拉");

        System.out.println("list = " + list);


    }



    @Test
    public void test02() {

        ArrayList<String> list = new ArrayList<>();

        Collections.addAll(list, "张三","李四","张三","张三","王五");
        System.out.println("list = " + list);// [张三, 李四, 张三, 王五]

        //查看指定元素出现的次数
        int count = Collections.frequency(list, "张三");
        System.out.println("count = " + count);

        //交换指定下标的值
        Collections.swap(list,0,4);

        System.out.println("list = " + list);
    }



    @Test
    public void test01(){

        ArrayList<Integer> list = new ArrayList<>();

        //批量添加
        Collections.addAll(list, 10,20,30,40,70,90);

        System.out.println("list = " + list);
        //洗牌
        Collections.shuffle(list);

        System.out.println("list = " + list);
        //排序
        Collections.sort(list);
        System.out.println("list = " + list);

        //二分查找  前提是有序
        int index = Collections.binarySearch(list, 300);
        System.out.println("index = " + index);


    }
}
