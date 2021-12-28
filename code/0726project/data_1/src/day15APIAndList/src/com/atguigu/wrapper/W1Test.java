package day15APIAndList.src.com.atguigu.wrapper;

import org.junit.Test;

import java.util.ArrayList;

/*
java 中数据类型
            基本数据类型
                byte        Byte
                short       Short
                int         Integer
                long        Long
                double      Double
                float       Float
                boolean     Boolean
                char        Character
            引用数据类型
为什么需要包装类型？
    因为 在某些情况下  基本数据类型是无法使用的。提出了包装类型
 可以将基本数据类型的值 变为 对象

 */
public class W1Test {

    @Test
    public void test01(){

        ArrayList<Integer> list = new ArrayList();

     /*   list.add("李白");

        list.add(10);*/
    }

    @Test
    public void test02(){


        String s = "李白";
        show(s);

        int m = 10;

        show(m);


    }

    public void show(Object o){




    }
}
