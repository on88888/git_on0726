package day10OOP.src.com.atguigu.thisandsuper.this1.t1;
/*
this:代指当前对象
           当前对象:谁调用就是谁
       this可以调用 属性
                    方法

注意：

    静态方法内不能使用this


 */
public class Test {
    public static void main(String[] args) {

        Student.cc();

        Student s1 = new Student("李白", 20);
        System.out.println("s1 = " + s1);

        s1.show();
        System.out.println("-----------------------");
        Student s2 = new Student();
        s2.show();


    }
}
