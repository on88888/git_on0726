package day12OOPAndException.src.com.atguigu.comparator.c1;

import java.util.Comparator;

public class SortOfAge implements Comparator {
    /*
    >0 前一个对象 > 后一个对象
    ==0 前一个对象 = 后一个对象
    <0 前一个对象 <后一个对象
     */
    @Override
    public int compare(Object o1, Object o2) {
        // Object o1 = d1;
        // Object o2 = d2;

        Dog d1 = (Dog)o1;
        Dog d2 = (Dog)o2;


        return d1.age-d2.age;
    }
}
