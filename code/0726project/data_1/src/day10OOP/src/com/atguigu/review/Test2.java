package day10OOP.src.com.atguigu.review;

public class Test2 {
    public static void main(String[] args) {


        Teacher t1 = new Teacher("王安石", 19, '男', 19999.9);
        String info = t1.getInfo();

        System.out.println("info = " + info);

        Student s2 = new Student("李白", 20, '男', 99);
        System.out.println("s2.getInfo() = " + s2.getInfo());
    }
}
