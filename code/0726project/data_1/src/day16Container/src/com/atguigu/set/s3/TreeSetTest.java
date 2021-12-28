package day16Container.src.com.atguigu.set.s3;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/*
Collection
    -- List  有序 不唯一
       -- ArrayList
       -- LinkedList
       -- Vector
       -- Statck

    -- Set  元素唯一
       -- HashSet  哈西表
            无序 ： 不是按照添加顺序展示
                    hash表内的顺序进行展示
            唯一： 数据不能重复

            注意： 1.存储自定义类型数据 必须 重写 hashCode() equals() 才能保证 无序唯一
                   2.存储自定义类型数据 有自定义属性 那么属性也需要重写   hashCode() equals() 才能保证 无序唯一

       -- LinekdHashSet  哈西表
            有序：添加顺序
            唯一：数据不能重复
            注意： 1.存储自定义类型数据 有自定义属性 那么属性也需要重写   hashCode() equals() 才能保证 无序唯一
                   2. LinekdHashSet 有序：底层有对象的前一个值和后一个值的地址引用
       -- TreeSet
            有序：自然顺序(从小到大)
            唯一：数据不能重复
                    根据比较规则确定重复的条件
                    年龄比较 年龄一致对象重复

       set:相较于 Collection 并没有新增方法
 */
public class TreeSetTest {

    @Test
    public void test03() {

        TreeSet<String> set = new TreeSet<>();

        set.add("libai");
        set.add("dufu");
        set.add("wanganshi");
        set.add("lishangyin");


        for (String s : set) {
            System.out.println("s = " + s);
        }

    }

    @Test
    public void test02(){

        TreeSet<String> set =new TreeSet<>();
        set.add("z");
        set.add("b");
        set.add("a");
        set.add("A");
        set.add("c");

        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()){
            String ele = iterator.next();
            System.out.println("ele = " + ele);
        }


    }



    @Test
    public void test01(){

        //多态
        Set<Integer> set = new TreeSet<>();
        //添加数据
        set.add(10);
        set.add(9);
        set.add(90);
        set.add(90);
        set.add(100);
        set.add(100);
        set.add(-1);


        //遍历
        for (Integer integer : set) {
            System.out.println("integer = " + integer);
        }



    }
}
