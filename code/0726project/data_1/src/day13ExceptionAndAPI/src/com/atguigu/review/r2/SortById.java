package day13ExceptionAndAPI.src.com.atguigu.review.r2;

import java.util.Comparator;

public class SortById implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Employee e1 = (Employee)o1;
        Employee e2 = (Employee)o2;

        return e1.id-e2.id;
    }
}
