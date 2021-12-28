package day12OOPAndException.src.com.atguigu.comparator.c2;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {


        Person p1 = new Person("李白", 20, 999.9);
        Person p2 = new Person("杜甫", 19, 999.6);
        Person p3 = new Person("王安石", 18, 999.5);
        Person p4 = new Person("李清照", 17, 999.3);
        Person p5 = new Person("夫人", 21, 999.1);

        Person[] ps = {p1, p2, p3, p4, p5};

        for (Person p : ps) {

            System.out.println("排序前 = " + p);
        }
        //创建比较规则对象
       /* SortBySalary sortBySalary = new SortBySalary();
        Arrays.sort(ps, sortBySalary);//Comparator c = sortBySalary;*/

       Arrays.sort(ps, new Comparator<Person>() {
           @Override
           public int compare(Person o1, Person o2) {
               return o1.age-o2.age;
           }
       });
        System.out.println();
        for (Person p : ps) {

            System.out.println("排序后 = " + p);
        }
    }
}
