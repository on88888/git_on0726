package day16Container.src.com.atguigu.list.arraylist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/*

ArrayList
    1.第一次创建ArrayList对象时 并没有开辟长度为10 的空间 而是一个空数组
          this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    2.第一次创建对象时才会给底层数组开辟空间 长度为10
        elementData = Arrays.copyOf(elementData, newCapacity);
        扩容到原来的1.5倍
     3.线程不安全  效率高
Vector
    第一次创建对象时 给底层数组开辟长度为10的空间
    扩容规则：
      1. 如果 capacityIncrement  是<=0  扩容到原来的2倍
      2.如果 capacityIncrement  是>0    扩容到  oldCapacity+capacityIncrement;
       int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                         capacityIncrement : oldCapacity);
      增长因子可以在创建对象的时候确定
        Vector<Object> objects = new Vector<>(10, 20);

      3. 线程安全的 效率低

相同点：
       1.底层都是使用Object[] 存储数据
       2.有共同的父亲  AbstractList
 */
public class ArrayListTest3 {

    @Test
    public void test02(){

        Vector<String> v = new Vector<>();

        v.add("李白");

        Vector<Object> objects = new Vector<>(10, 20);




    }

    @Test
    public void test01(){
        //创建集合对象
        ArrayList<String> list = new ArrayList<>();

        list.add("张三");
        list.add("李四");
        list.add("王五");

        list.remove("李四");

        list.add(1,"赵六");



    }
}
