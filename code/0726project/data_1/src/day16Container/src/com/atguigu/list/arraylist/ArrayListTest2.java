package day16Container.src.com.atguigu.list.arraylist;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ArrayListTest2 {
    //进行作用域提升 飞方便在所有的方法内使用 list
    List<String> list;
    @Test
    public void test01(){

        for (String s : list) {

            System.out.println("s = " + s);
        }

    }

    @Test
    public void test02() {

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()){
            String ele = iterator.next();
            System.out.println("ele = " + ele);
        }

    }

    @Test
    public void test03(){
       /* String s = list.get(list.size() - 1);
        System.out.println("s = " + s);
        System.out.println("list.get(3) = " + list.get(3));*/
        //i: 代表集合内元素的下标
       for(int i = 0;i<list.size();i++){
           String ele = list.get(i);
           System.out.println("ele ---> " + ele);
       }

    }

    @Test
    public void test04(){

       /* list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(" >>>>>"+ s);
            }
        });*/

     //  list.forEach((a)-> System.out.println(a));

        list.forEach(System.out::println);



    }



    @Before
    public void test00(){
        System.out.println("--- Before -----");

        list = new ArrayList<>();

        list.add("张三");//0
        list.add("李四");//1
        list.add("王五");//2
        list.add("赵六");//3

    }
}
