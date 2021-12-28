package day16Container.src.com.atguigu.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/*
新建一个 Dog 类 内部有两个属性  name  age

创建4个对象 存储到  集合内

使用增强for 遍历

使用迭代器遍历 并删除  age >3 的 数据

查看集合内元素的数量

将剩余元素转为数组存储
 */
public class Test {

    public static void main(String[] args) {
        //1.创建对象
        Dog d1 = new Dog("旺财", 2);
        Dog d2 = new Dog("小黑", 3);
        Dog d3 = new Dog("小黄", 4);
        Dog d4 = new Dog("小白", 5);

        //2.创建集合对象
        Collection<Dog> c = new ArrayList<>();

        //3.将数据存储到集合内
        c.add(d1);
        c.add(d2);
        c.add(d3);
        c.add(d4);

        //4.使用增强for 遍历
        for (Dog dog : c) {
            System.out.println(dog);
        }

        //5.使用迭代器遍历
        System.out.println("-------");

        Iterator<Dog> iterator = c.iterator();
        while (iterator.hasNext()) {

            Dog dog = iterator.next();
            //6.校验 小狗对象的年龄是否大于3
            if(dog.getAge()>3){
                iterator.remove();
            }
        }

        //7.查看集合内元素的数量

        int size = c.size();

        System.out.println("size = " + size);

        //8.将剩余元素转为数组存储

        Dog[] dogs = c.toArray(new Dog[]{});

        System.out.println("Arrays.toString(dogs) = " + Arrays.toString(dogs));

    }

}
