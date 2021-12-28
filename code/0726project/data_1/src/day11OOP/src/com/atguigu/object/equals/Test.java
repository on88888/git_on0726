package day11OOP.src.com.atguigu.object.equals;

/*
equals: 比较两个对象是否相等
    重写前 调用的是Object类中的equals 比较的是地址值 (this == obj);
    重写后 比较的是属性值
== :
    基本数据类型  比较的是数值
    引用数据类型  比较的是地址值
 */
public class Test {
    public static void main(String[] args) {

        Student s1 = new Student("李白", 21);
        Student s2 = new Student("李白", 21);
        System.out.println(s1==s2);

        boolean equals = s1.equals(s2);
        System.out.println("equals = " + equals);//true  false

        int m = 20;
        int n = 20;
        System.out.println(m == n);
    }
}
