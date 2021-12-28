package day12OOPAndException.src.com.atguigu.comparator.c1;
/*
Comparator:外部（定制）比较器 可以在比较对象类的外部完成比较规则的制定
    1.创建一个比较规则类 实现Comparator接口

    2.重写 compare(Object o1,Object o2);

    3.在需要比较对象的位置 创建比较规则类的对象

    4.此对象调用 compare();

 */
public class Test {
    public static void main(String[] args) {

        Dog d1 = new Dog("小黑", 3);
        Dog d2 = new Dog("小白", 2);

        //创建比较规则对象
        SortOfAge sortOfAge = new SortOfAge();

        int compare = sortOfAge.compare(d1, d2);

        System.out.println("compare = " + compare);

    }
}
