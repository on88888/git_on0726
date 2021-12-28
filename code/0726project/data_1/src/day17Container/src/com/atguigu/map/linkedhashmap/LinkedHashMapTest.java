package day17Container.src.com.atguigu.map.linkedhashmap;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
LinkedHashMap:
            key 有序：添加顺序
                唯一： 数据不重复

            value:满足泛型要求即可

 */
public class LinkedHashMapTest {

    @Test
    public void test02(){

        //1.创建集合对象
        LinkedHashMap<String,String> map = new LinkedHashMap<>();

        //2.添加数据

        map.put("ch", "china");
        map.put("ru", "russia");
        map.put("ja", "japan");

        System.out.println(map);

        //获取所有的key
        Set<String> keySet = map.keySet();
        //遍历set 获取单个的key
        Iterator<String> iterator = keySet.iterator();

        while (iterator.hasNext()){
            String key = iterator.next();
             //通过key 获取指定的value
            String value = map.get(key);

            System.out.println(key +"  "+ value);
        }


    }


    @Test
    public void test01(){
        //1.创建集合对象
        LinkedHashMap<Integer,String> map = new LinkedHashMap<>();
        //2.添加数据

        map.put(10, "666");
        map.put(1, "张三");
        map.put(4, "李四");
        map.put(7, "王五");
        map.put(10, "李商隐");
        //3.展示
        System.out.println("map = " + map);

        //获取所有的键值对对象
        Set<Map.Entry<Integer, String>> entries = map.entrySet();

        //遍历set 获取单个的键值对

        for (Map.Entry<Integer, String> entry : entries) {

            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
            System.out.println("-------");
        }


    }
}
