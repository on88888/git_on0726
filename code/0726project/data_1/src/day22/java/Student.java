package day22.java;

public class Student extends Person {

    public Student(){
        System.out.println("Student()");
    }

    public Student(int a){
        System.out.println("Student(int a)");
    }

    @Override
    public String toString() {
        return "Student{" +
                "sname='" + sname + '\'' +
                ", sage=" + sage +
                '}';
    }

    public String sname = "Student";
    private int sage = 20;

    public void publicTest(String info,int number){
        System.out.println("publicTest===" + info + "======" + number);
    }
    private void privateTest(){
        System.out.println("privateTest");
    }

    @MyAnn
    public void run(){
        System.out.println("sname=" + sname);
        System.out.println("sage=" + sage);
    }
}
