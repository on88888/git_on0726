package day09OOP.src.com.atguigu.encap.exer;

public class Dog {
    private String name;
    private int age;
    private String color;

    public void setColor(String color){
        this.color=color;

    }
    public String getColor(){
        return color;
    }


    public void setAge(int age){

        if(age>=1&& age<=30){
            this.age=age;
        }else{
            System.out.println("输入的年龄有误");
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

}
