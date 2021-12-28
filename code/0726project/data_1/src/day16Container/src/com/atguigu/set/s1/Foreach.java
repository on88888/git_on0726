package day16Container.src.com.atguigu.set.s1;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Foreach {
    @Test
    public void test02() {

        //多态
        Set<String> set = new HashSet<>();

        set.add("张三");
        set.add("李四");
        set.add("王五");
        set.add("赵六");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){

            String ele = iterator.next();
            System.out.println("迭代器 = " + ele);
        }


    }

    @Test
    public void test01() {
        //多态
        Set<String> set = new HashSet<>();

        set.add("张三");
        set.add("李四");
        set.add("王五");
        set.add("赵六");

        for (String s : set) {

            System.out.println("增强for  = " + s);
        }


    }
}
