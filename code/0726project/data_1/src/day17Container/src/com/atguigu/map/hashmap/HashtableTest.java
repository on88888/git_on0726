package day17Container.src.com.atguigu.map.hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
/*
HashMap
Hashtable
相同点：
    底层都是采用哈西表存储
    key 无序 唯一
不同点：
    1. Hashtable  key value 都不能为null
       HashMap key value 都能为null


    2.HashMap 线程不安全的  效率高
     Hashtable 线程安全的 效率低

 */
public class HashtableTest {


    @Test
    public void test02(){
        //创建集合对象
        HashMap<String,String> map = new HashMap<>();
        //添加数据
        map.put("ch", "china");
        map.put("ja", "chinese");
        map.put("ru", "russia");

        map.put("ch", "chinese");
        map.put("ru", "俄罗斯");

        map.put(null, "666");
        map.put("666", null);
        map.put(null, null);
        System.out.println(map);


    }

    @Test
    public void test01(){
        //创建集合对象
        Hashtable<String,String> map = new Hashtable<>();

        //填充数据
        //添加数据
        map.put("ch", "china");
        map.put("ja", "chinese");
        map.put("ru", "russia");

        map.put("ch", "chinese");
        map.put("ru", "俄罗斯");

       // map.put(null, "666");

        //map.put("666", null);
        map.put("666", null);
        System.out.println(map);
    }
}
