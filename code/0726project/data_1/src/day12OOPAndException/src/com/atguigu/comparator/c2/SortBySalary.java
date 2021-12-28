package day12OOPAndException.src.com.atguigu.comparator.c2;

import java.util.Comparator;


public class SortBySalary implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Person p1 = (Person)o1;
        Person p2 = (Person)o2;

        return Double.compare(p1.salary, p2.salary);
    }
}
