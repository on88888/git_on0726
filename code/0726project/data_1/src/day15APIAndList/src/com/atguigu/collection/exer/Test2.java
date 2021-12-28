package day15APIAndList.src.com.atguigu.collection.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Test2 {

    @Test
    public void test01(){

        Collection<Student> list = new ArrayList<>();

        list.add(new Student("张三", 90));
        list.add(new Student("李四", 50));
        list.add(new Student("王五", 70));
        list.add(new Student("赵六", 30));
        //增强for遍历
        for (Student s : list) {
            System.out.println("s = " + s);
        }

        //创建迭代器对象
        Iterator<Student> i = list.iterator();
        while (i.hasNext()){

            Student student = i.next();

            //判断成绩
            if(student.score<60){
                i.remove();
            }
        }

        System.out.println("list = " + list);


    }
}
