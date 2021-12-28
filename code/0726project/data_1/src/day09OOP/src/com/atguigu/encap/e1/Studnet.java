package day09OOP.src.com.atguigu.encap.e1;

public class Studnet {

    public String name = "张三";

    protected int age = 20;

    double score = 90.9;

    private char sex='男';


    public void show(){

        System.out.println(sex+name+age+score);
    }
}
