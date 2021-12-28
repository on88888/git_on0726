package day13ExceptionAndAPI.src.com.atguigu.review.r2;

import java.util.Arrays;
import java.util.Comparator;

/*
声明一个Employee员工类，包含编号 int 、姓名 String 、薪资 double，

声明一个测试类，在main中，创建Employee[]数组，长度为5，显示原来顺序结果

调用java.util.Arrays数组工具类的排序方法public static void sort(Object[] a, Comparator c)
对数组的元素进行排序，用匿名内部类的对象给c形参传入按照编号比较大小的定制比较器对象。并显示排序后结果

调用java.util.Arrays数组工具类的排序方法public static void sort(Object[] a, Comparator c)
对数组的元素进行排序，用匿名内部类的对象给c形参传入定制比较器对象  首先按照薪资比 薪资相同按照 编号比

 */
public class Test {
    public static void main(String[] args) {
        Employee e1 = new Employee(3, "张三", 9999.9);
        Employee e2 = new Employee(5, "李四", 9999.6);
        Employee e3 = new Employee(1, "王五", 9999.7);
        Employee e4 = new Employee(4, "赵六", 9999.9);
        Employee e5 = new Employee(2, "安琪拉", 9999.6);
        Employee [] es = {e1,e2,e3,e4,e5};

       // SortById sortById = new SortById();

        for (Employee e : es) {
            System.out.println("排序前： "+ e);
        }


       /* Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Employee e1 = (Employee) o1;
                Employee e2 = (Employee) o2;

                return e1.id - e2.id;
            }
        };*/

        Arrays.sort(es,new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Employee e1 = (Employee) o1;
                Employee e2 = (Employee) o2;

                return e1.id - e2.id;
            }
        });
        System.out.println("-------");
        for (Employee e : es) {
            System.out.println("排序后： "+ e);
        }
    }

}
