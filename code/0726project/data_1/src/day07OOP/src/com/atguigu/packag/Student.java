package day07OOP.src.com.atguigu.packag;

public class Student {
        String name = "PACKAGE_NAME";
        int age ;
        public static void main(String[] args) {
                Student s1 = new Student();
                s1.name="王安石";
                System.out.println("s1.name = " + s1.name);
                System.out.println("s1.age = " + s1.age);

                Student s2 = s1;
                s2.age=18;
                System.out.println("s1.age = " + s1.age);

        }
}
