package day15APIAndList.src.com.atguigu.collection.c1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/*

容器
Collection: 集合的顶级接口
    -- List 接口
            有序 不唯一
             类
        -- ArrayList
        -- LinkedList
        -- Vector


    -- Set  接口
            无序  唯一


增加
删除
查找
清空
数量
交集
转为数组
 */
public class Test {

    @org.junit.Test
    public void test05(){

        Collection c = new ArrayList();

        c.add("张三");
        c.add("李四");
        c.add("王五");

        System.out.println(c);
        //将集合转为数组
        Object[] objects = c.toArray();

        System.out.println("objects.length = " + objects.length);

        System.out.println("Arrays.toString(objects) = " + Arrays.toString(objects));

        System.out.println("c = " + c);
        //清空集合
        c.clear();

        System.out.println("c = " + c);
    }
    @org.junit.Test
    public void test04(){
        Collection c = new ArrayList();
        c.add("张三");
        c.add("李四");
        c.add("王五");
        Collection c1 = new ArrayList();

        c1.add("李四");
        c1.add("王五");

        //求交集
        c.retainAll(c1);
        System.out.println(c);
    }


    @org.junit.Test
    public void test03(){

        Collection c = new ArrayList();

        c.add("张三");
        c.add("李四");
        c.add("王五");
        //判断指定元素是否在集合内出现过
        boolean contains = c.contains("李四1");

        System.out.println("contains = " + contains);


    }



    @org.junit.Test
    public void test02(){

        Collection c1 = new ArrayList();

        c1.add("张三");
        c1.add("李四");
        c1.add("王五");

        //删除指定的元素
     /*   c1.remove("张三");

        System.out.println("c1 = " + c1);*/

        Collection c2 = new ArrayList();

        c2.add("安琪拉");
        c2.add("嬴政");
        c2.add("米莱迪");

       // c1.add(c2);
        //添加集合
        c1.addAll(c2);

        System.out.println("c1.size() = " + c1.size());
        System.out.println("c1 = " + c1);//[张三, 李四, 王五, 安琪拉, 嬴政, 米莱迪]

        //删除集合
        c1.removeAll(c2);

        System.out.println("c1 = " + c1);


    }




    @org.junit.Test
    public void test01(){

        //多态
        Collection c = new ArrayList();
        // 判断集合是否为空
        boolean empty = c.isEmpty();

        System.out.println("empty = " + empty);

        System.out.println("c = " + c);
        //添加
        c.add(10);
        c.add(LocalDate.now());
        c.add("张三");

        System.out.println("c = " + c);
        //获取集合内元素的数量
        int size = c.size();
        System.out.println("size = " + size);


    }
}
