package day17Container.src.com.atguigu.map.m1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Foreach {


    @Test
    public void test02(){
        HashMap<String,Integer>  map = new HashMap<>();
        //添加数据
        map.put("李白", 100);
        map.put("杜甫", 200);
        map.put("王安石", 900);
        map.put("李商隐", 700);
        //获取所有的键值对
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        //遍历set
        Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();

        while (iterator.hasNext()){

            Map.Entry<String, Integer> entry = iterator.next();

            String key = entry.getKey();

            Integer value = entry.getValue();

            System.out.println(key+" ---> "+ value);


        }



    }



    @Test
    public void test01(){
        Map<String,Integer>  map = new HashMap<>();

        //添加数据
        map.put("李白", 100);
        map.put("杜甫", 200);
        map.put("王安石", 900);
        map.put("李商隐", 700);

        //获取所有的key
        Set<String> keys = map.keySet();

        //遍历key
        for (String key : keys) {
            //key 获取 value
            Integer value = map.get(key);

            System.out.println(key+" ---> "+ value);
        }

    }
}
