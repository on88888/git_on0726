package day08OOP.src.com.atguigu.method.m1;

public class Student {

    double score;

    public void test(){
        System.out.println("this is test()");
        show("李四");
    }


    public void show(String name){
        System.out.println("this is show() name = "+ name);
    }


    public int getSum(int n,int m){

        int sum = n+m;
        return sum;
    }
}
