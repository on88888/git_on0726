package day11OOP.src.com.atguigu.object.hashcode;
/*
获取当前对象的哈西码
   哈西码 对象不同肯定不同
   哈西码相同 对象也不一定相同

如果两个对象的属性值完全一样  那么就认为是同一个对象
如何实现 重写即可
 */
public class Test {
    public static void main(String[] args) {

        Person p1 = new Person("李白", 20);
        Person p2 = new Person("李白", 20);

        System.out.println("p1.hashCode() = " + p1.hashCode());
        System.out.println("p2.hashCode() = " + p2.hashCode());

        System.out.println("\"Aa\".hashCode() = " + "Aa".hashCode());
        System.out.println("\"BB\".hashCode() = " + "BB".hashCode());


    }
}
