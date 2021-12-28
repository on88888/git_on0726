package day17Container.src.com.atguigu.map.set;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetTest {

    @Test
    public void test01(){
        /*
        底层采用的是 HashMap
        将存储的值放到了 Map key 的位置
         */
        HashSet set = new HashSet();

        set.add("aa");

        set.add("cc");
    }

    @Test
    public void test02(){
            /*
        底层采用的是 LinkedHashMap
        将存储的值放到了 Map key 的位置
         */
        LinkedHashSet linkedHashSet = new LinkedHashSet();

        linkedHashSet.add("a");

    }

    @Test
    public void test03(){
    /*
         底层采用的是 TreeMap
         将存储的值放到了 Map key 的位置
         */
        TreeSet  set = new TreeSet();

        set.add("a");
    }
}
