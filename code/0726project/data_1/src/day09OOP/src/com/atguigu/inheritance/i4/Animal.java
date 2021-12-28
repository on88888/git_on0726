package day09OOP.src.com.atguigu.inheritance.i4;

public class Animal {
    String name;

    public String getInfo(){

        return "名字是："+name;
    }
}
class Dog extends Animal{

    String color;


    public String getInfo() {
        //super：调用从父类继承的方法
        String info = super.getInfo();
        return info+",颜色是："+color;

    }
}