package day17Container.src.com.atguigu.map.treemap;

import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
TreeMap
        key: 有序  自然顺序
             唯一   不重复
         value:满足泛型要求即可

 */
public class TreeMapTest {

    @Test
    public void test02(){

        TreeMap<Character,Integer> map = new TreeMap<>();

        map.put('z', 10);
        map.put('A', 60);
        map.put('C', 70);
        map.put('b', 90);

        Set<Character> keySet = map.keySet();

        for (Character c : keySet) {

            Integer value = map.get(c);
            System.out.println(c+"   "+ value);
        }


    }


    @Test
    public void test01(){
    //1.创建 TreeMap 对象
        TreeMap<Integer,String> map = new TreeMap<>();
    //2.添加数据
        map.put(10, "张三");
        map.put(11, "李四");
        map.put(9, "王五");
        map.put(7, "赵六");
        System.out.println(map);

    //3.展示数据

        Set<Map.Entry<Integer, String>> entries = map.entrySet();

        for (Map.Entry<Integer, String> entry : entries) {

            String value = entry.getValue();
            Integer key = entry.getKey();

            System.out.println(key  +"   "+ value);
        }
    }
}
