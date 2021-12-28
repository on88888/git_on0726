package day11OOP.src.com.atguigu.interface1.cloneable;

/*
实现对象克隆 做两件事
  1.实现Cloneable 接口
  2.重写clone()
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {

        Student s1 = new Student("李白", 20);
        System.out.println("s1 = " + s1);
       /*     Student s2 = s1;

        s2.name="王安石";

        System.out.println("s1 = " + s1);*/
        Object clone = s1.clone();

        System.out.println("clone = " + clone);
        Student s3 = (Student)clone;
        s3.name="王安石";

        System.out.println("s3 = " + s3);

        System.out.println("s1 = " + s1);


    }
}
