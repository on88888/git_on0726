package day17Container.src.com.atguigu.map.m1;

import java.util.*;

/*
Collection
   -- List
   -- Set


Map 顶级接口
    存储映射关系 键值对
    -- HashMap
    -- LinekdHashMap
    -- TreeMap


 */
public class Test {


    @org.junit.Test
    public void test02(){

        Map<Integer,String> map = new HashMap<>();
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王五");
        //获取所有的key
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            System.out.println("key = " + key);
        }
        //获取所有的value
        Collection<String> values = map.values();
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()){
            String value = iterator.next();
            System.out.println("value = " + value);
        }
        //根据key获取value
        String value = map.get(1);
        System.out.println("value = " + value);
    }
    @org.junit.Test
    public void test01(){
        Map<String,String> map = new HashMap<>();
        //判断集合是否为空
        System.out.println("map.isEmpty() = " + map.isEmpty());
        //添加数据
        map.put("ch","china");
        map.put("ja", "japan");
        map.put("ru", "russia");
        System.out.println(map);
        //返回键值对的数量
        System.out.println("map.size() = " + map.size());
        //判断集合是否为空
        System.out.println("map.isEmpty() = " + map.isEmpty());
        //根据key 删除数据
        map.remove("ja");
        System.out.println("map = " + map);
        //查找数据
        //判断key 是否在map 内
        boolean ch = map.containsKey("ch");
        System.out.println("ch = " + ch);
        //判断value 是否在map 内
        boolean containsValue = map.containsValue("russia");
        System.out.println("containsValue = " + containsValue);
        //修改
        //根据key修改指定的value
        map.replace("ch", "chinese");
        System.out.println("map = " + map);

    }

}
