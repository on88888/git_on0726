package day21.java6;


class Person{
    public String pname = "person";
    private int page = 10;

    public void publicSay(){
        System.out.println("publicSay");
    }
    private void privateSay(){
        System.out.println("privateSay");
    }
}

public class Student extends Person {
    public String sname = "Student";
    private int sage = 20;

    public void publicTest(String info,int number){
        System.out.println("publicTest===" + info + "======" + number);
    }
    private void privateTest(){
        System.out.println("privateTest");
    }

    public void run(){
        System.out.println("sname=" + sname);
        System.out.println("sage=" + sage);
    }
}
