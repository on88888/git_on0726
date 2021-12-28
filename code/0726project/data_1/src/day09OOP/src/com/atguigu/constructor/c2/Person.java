package day09OOP.src.com.atguigu.constructor.c2;

public class Person {
    private String name;
    private int age;

    Person(){}

    public Person(String name,int age){
        this.name=name;
       // this.age=age;
        setAge(age);
    }

    public void setAge(int age){
        if(age>=1 && age<=150){
            this.age=age;
        }else{
            System.out.println("年龄输入有误");
        }
    }
    public int getAge(){
        return age;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public void showInfo(){


        System.out.println("名字是："+name+"，年龄是："+age);
    }

}
