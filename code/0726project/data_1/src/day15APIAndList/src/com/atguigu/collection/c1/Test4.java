package day15APIAndList.src.com.atguigu.collection.c1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/*
想要使用 增强for 需要是 Iterable 接口的孩子

Collection 所有的孩子 都可以使用 增强for
public interface Collection<E> extends Iterable<E>

增强for底层采用的是迭代器遍历
 */
public class Test4 {
    /*

    for(集合元素类型 变量名： 集合名){
        变量名:集合内的元素
    }

     */
    @Test
    public void test01(){

        Collection<String> c = new ArrayList<>();
        c.add("张三");
        c.add("李四");
        c.add("王五");
        c.add("赵六");

        for(String ele: c){
            System.out.println(ele);
        }

    }

    @Test
    public void test02(){
        Collection<String> c = new ArrayList<>();
        c.add("张三");
        c.add("李四");
        c.add("王五");
        c.add("赵六");

        for (String s : c) {

        }




    }
}
class A implements Iterable{


    @Override
    public Iterator iterator() {
        return null;
    }
}