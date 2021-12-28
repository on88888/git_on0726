package day09OOP.src.com.atguigu.constructor.c1;

public class Student {
    String name;
    int age;
    double score;
    double height;
    public Student(){
        System.out.println("无参执行了");
    }

    public Student(String name,int age,double score,double height){
        this.name=name;
        this. age=age;
        this.  score=score;
        this.  height=height;
    }

    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }



    public void showInfo(){
        System.out.println("名字是："+name+",年龄是："+ age+"，分数是："+score+",身高是："+height);
    }

}
