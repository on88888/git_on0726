package day10OOP.src.com.atguigu.init.classinit.c2;

public class Person {
    static String name = getName();
    private static String getName() {
        System.out.println("Person static getName");
        return null;
    }
    static {
        System.out.println("static Person 1");
    }
    static int age = getAge();
    private static int getAge() {
        System.out.println("Person static getAge");
        return 10;
    }
    public static void cc(){
        System.out.println("this is static cc()");
    }

}
class Student extends Person{
    static {
        System.out.println("static  Student 1");
    }
    static String name = getName();
    private static String getName() {
        System.out.println("Student static getName");
        return null;
    }
    static {
        System.out.println("Student  Student 2");
    }

    public static void show(){
        System.out.println("this is show in Student");
    }
}
