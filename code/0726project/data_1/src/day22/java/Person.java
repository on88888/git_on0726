package day22.java;

public class Person{
    public String pname = "person";
    private int page = 10;

    public void publicSay(){
        System.out.println("publicSay");
    }
    private void privateSay(){
        System.out.println("privateSay");
    }
}
