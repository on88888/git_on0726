package day17Container.src.com.atguigu.map.hashmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Map
    -- HashMap
       key :
           无序 : 不是添加顺序
           唯一： key不能重复
       value：
           只要满足泛型要求即可
      注意： 如果key 重复 新的value 会替换旧的value
 */
public class HashMapTest {
    @Test
    public void test01(){
/*        //创建集合对象
        HashMap<String,String> map = new HashMap<>();

        //添加数据
        map.put("ch", "china");
        map.put("ja", "chinese");
        map.put("ru", "russia");

        map.put("ch", "chinese");
        map.put("ru", "俄罗斯");

        System.out.println(map);*/

        HashMap<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        
        list.add("v11");
        list.add("v22");
        list.add("v33");
        list.add("v44");
        list.add("v55");
        list.add("v66");
        System.out.println("list = " + list);

        map.put("key",list);//给map赋值
        map.put("key1",list);//给map赋值
        map.put("key2",list);//给map赋值
        map.put("key3",list);//给map赋值
        boolean vlaues = map.get("key").add("111");//获取map中键值为“key”的值
        boolean vlaues1 = map.get("key").add("222");//获取map中键值为“key”的值
        System.out.println(vlaues );//输出结果
        System.out.println("map = " + map);

    }
}
