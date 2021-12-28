package day17Container.src.com.atguigu.map.hashmap;

import org.junit.Test;

import java.util.HashMap;

/*
  1. 创建HashMap
        将默认的负载因子 0.75f 赋值给了成员变量

  2. 数据添加
        第一次添加时 给底层Node[] 16
        threshold 12

  2.1 key null
       将数据添加到哈西表内下标为0的位置
  2.2 key 非null
      2.2.1 插入位置没有数据
                直接添加
      2.2.2 插入位置有数据 key 不一样
            原有节点的后面进行追加
              七上八下 jdk8  下 后面追加

      2.2.3 插入位置有数据 key相同
            将新的value 替换 旧的value 并将旧的value返回
      2.2.4
           扩容
            当集合中元素的数量>阈值  触发扩容
                 数组<<1  阈值<<1
           及
           树化
                0.插入位置节点的数量>=8
                1. 底层数组长度 >=64
                3. 插入新的数据  新数据的位置不能为null

   */
class Data{
    private String name;
    private  int num;

    public Data(String name, int num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        if (num != data.num) return false;
        return name != null ? name.equals(data.name) : data.name == null;
    }

    @Override
    public int hashCode() {
       if(num%2==0){
           return 1;
       }
       return num;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}

public class SourceHashMap {

    @Test
    public void test02(){
        HashMap<Data,String> map = new HashMap<>();
        map.put(null, "A");
        for (int i = 0; i < 1000; i++) {
            Data data = new Data("i " + i, i);
            map.put(data, "i"+i);
        }
    }

    @Test
    public void test01(){
        HashMap<String,String> map = new HashMap<>();
        map.put(null, "张三");
        map.put("ch", "china");
        map.put("ru", "russia");
        String oldValue = map.put("ru", "俄罗斯");
        System.out.println("oldValue = " + oldValue);
        map.put("ja", "japan");

    }

}
