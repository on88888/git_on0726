package day16Container.src.com.atguigu.list.linkedlist;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class TestForeach {
    ArrayList<String> list;

    @Test
    public void test05(){

        ListIterator<String> listIterator = list.listIterator();

        while (listIterator.hasNext()){

            //获取当前元素前一个下标
            int previousIndex = listIterator.previousIndex();

            String ele = listIterator.next();
            //获取当前元素后一个下标
            int nextIndex = listIterator.nextIndex();

            System.out.println("previousIndex = "+ previousIndex+" , ele = "+ele+" , nextIndex = "+ nextIndex);
        }
    }

    @Test
    public void test04(){
        /*
        ListIterator: 专门为遍历List集合而生

         */
        ListIterator<String> listIterator = list.listIterator();

        while (listIterator.hasNext()){

            String next = listIterator.next();

            System.out.println("next = " + next);
        }

        System.out.println("-----");
        while (listIterator.hasPrevious()){
            String previous = listIterator.previous();
            System.out.println("previous = " + previous);
        }

    }



    @Before
    public void test00(){

        list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        System.out.println("数据添加完毕\n");
    }

    @Test
    public void test01(){
        for (String s : list) {
            System.out.println("增强for = " + s);
        }

    }

    @Test
    public void test02(){

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()){

            String ele = iterator.next();
            System.out.println("迭代器 = " + ele);
        }


    }

    @Test
    public void test03(){

        for (int i = 0; i < list.size(); i++) {

            String s = list.get(i);
            System.out.println("普通for = " + s);
        }

    }


}
