package day15APIAndList.src.com.atguigu.collection.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Test1 {

    @Test
    public void test01(){

        Collection<Integer> list = new ArrayList<>();

       l: for (int i = 2; i <101 ; i++) {

            for (int j = 2; j <i ; j++) {

                if(i%j==0){
                    continue l;
                }

            }

           //System.out.println(i);
           //将质数添加到集合内
           list.add(i);
        }

        //增强for遍历
      /*  for (Integer i : list) {

            System.out.println("i = " + i);
        }*/
        //迭代器遍历

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()){


            Integer ele = iterator.next();

            if(ele%10==3){
                iterator.remove();
            }

            if(ele==11){
                iterator.remove();
            }
        }

        System.out.println("剩余元素数量是：  " + list.size());
        System.out.println("list = " + list);

        //创建集合
        Collection<Integer> list2 = new ArrayList<>();


        for (int i = 0; i < 10; i++) {

           int num =  (int)(Math.random()*(100-2+1)+2);
           list2.add(num);
        }
        System.out.println("list2 = " + list2);

        //求交集
        list.retainAll(list2);

        System.out.println("list = " + list);
    }
}
