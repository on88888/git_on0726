package day15APIAndList.src.com.atguigu.collection.c1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Test5 {
/*
ConcurrentModificationException: 并发修改异常
  final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
    }


    modCount：记录的是对集合元素增加删除的次数


    int expectedModCount = modCount;
    expectedModCount:
 */

    @Test
    public void test02(){


        ArrayList<String >c = new ArrayList<>();
        c.add("张三");
        c.add("李四");
        c.add("王五");
        c.add("赵六");
        c.add("安琪拉");

        Iterator<String> iterator = c.iterator();


        while (iterator.hasNext()){
            String ele = iterator.next();
            if(ele.equals("王五")){
                //通过迭代器 的remove()进行删除
                iterator.remove();
            }
        }

        System.out.println("c = " + c);


    }


    @Test
    public void test01(){

        ArrayList<String >c = new ArrayList<>();
        c.add("张三");
        c.add("李四");
        c.add("王五");
        c.add("赵六");
        c.add("安琪拉");

        // modCount:5

        Iterator<String> iterator = c.iterator();

        while (iterator.hasNext()){

            String ele = iterator.next();

            if(ele.equals("王五")){
                c.remove(ele);
            }

            System.out.println("ele = " + ele);
        }
    }
}
