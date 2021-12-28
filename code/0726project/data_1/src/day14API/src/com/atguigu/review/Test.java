package day14API.src.com.atguigu.review;


import com.sun.javafx.css.Combinator;

import java.util.Arrays;
import java.util.Comparator;

/*
新建一个类Person
        String name
        int age
        double salary
        char sex  性别 只能是男 或者女 抛出性别异常
  在测试类中 新建  5个人对象 存储到  数组内  对数组元素进行排序
  即使发生异常 不能影响后面代码执行
    排序前输出
      Arrays.sort () 可以采用匿名内部类实现
     首先按照 年龄排序  年龄一致按照薪水排序
    排序后输出
 */
public class Test {
    public static void main(String[] args) {


        Person p1 = new Person("张三", 29, 9999.9, '男');
        Person p2 = new Person("李四", 19, 9999.1, 'A');
        Person p3 = new Person("赵武", 20, 9999.6, '女');
        Person p4 = new Person("王五", 19, 9999.7, '男');
        Person p5 = new Person("赵六", 20, 9999.3, 'A');

        Person [] ps = {p1,p2,p3,p4,p5};


        for (Person p : ps) {
            System.out.println("排序前 = " + p);
        }

        System.out.println("----");
        //排序
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Person p1 = (Person) o1;
                Person p2 = (Person) o2;

                if (p1.getAge() == p2.getAge()) {
                    return Double.compare(p1.getSalary(), p2.getSalary());
                }
                return p1.getAge() - p2.getAge();
            }
        };
        Arrays.sort(ps,comparator);


        for (Person p : ps) {
            System.out.println("排序后 = " + p);
        }
    }
}
