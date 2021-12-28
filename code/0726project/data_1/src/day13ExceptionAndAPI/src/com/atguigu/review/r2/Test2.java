package day13ExceptionAndAPI.src.com.atguigu.review.r2;

import java.util.Arrays;
import java.util.Comparator;

public class Test2 {
    public static void main(String[] args) {
        Employee e1 = new Employee(3, "张三", 9999.9);
        Employee e2 = new Employee(5, "李四", 9999.6);
        Employee e3 = new Employee(1, "王五", 9999.7);
        Employee e4 = new Employee(4, "赵六", 9999.9);
        Employee e5 = new Employee(2, "安琪拉", 9999.6);
        Employee [] es = {e1,e2,e3,e4,e5};

        for (Employee e : es) {
            System.out.println("排序前： "+ e);
        }
        System.out.println("---");

        Comparator comparator = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Employee e1 = (Employee) o1;
                Employee e2 = (Employee) o2;
                //当薪资相同时
                if (Double.compare(e1.salary, e2.salary) == 0) {
                    //按照编号比
                    return e1.id - e2.id;
                }

                return Double.compare(e1.salary, e2.salary);
            }
        };

        Arrays.sort(es,comparator);



        for (Employee e : es) {
            System.out.println("排序后： "+ e);
        }
    }
}
