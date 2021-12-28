package day12OOPAndException.src.com.atguigu.comparable.c1;
/*
Comparable:内部比较器

    1.让需要比较对象的类 实现Comparable接口

    2.重写compareTo（）方法

    3.在方法内完成比较规则的制定
        >0  前一个对象>后一个对象
        ==0 前一个对象==后一个对象
        <0 前一个对象<后一个对象


Comparator：外部比较器



 */
public class Test {
    public static void main(String[] args) {

        Person p1 = new Person("李白", 25, 9999.9);
        Person p2 = new Person("杜甫", 23, 9999.6);

        int i = p1.compareTo(p2);

        System.out.println("i = " + i);


    }
}
