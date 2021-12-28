package day15APIAndList.src.com.atguigu.collection.c1;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Test2 {

    @Test
    public void test02(){
        //泛型 规定输入输入的类型

        // Collection<数据类型> 注意： 数据类型 不能为基本数据类型

        Collection<Integer> c3 = new ArrayList<>();

        Collection<String> c1 = new ArrayList<>();

        c1.add("李白");
        c1.add("杜甫");

        System.out.println("c1 = " + c1);
        c1.remove("李白");

        System.out.println(c1);

        Collection<LocalDate> c2 = new ArrayList<>();
        c2.add(LocalDate.now());


    }


    @Test
    public void test01(){

        Collection c = new ArrayList();

        c.add("张三");

        c.add(3.14);

        c.add(new Date());

        c.add(LocalDateTime.now());


    }
}
