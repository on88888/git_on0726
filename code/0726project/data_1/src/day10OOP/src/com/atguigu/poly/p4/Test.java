package day10OOP.src.com.atguigu.poly.p4;

import java.util.Scanner;

/*
多态应用在返回值类型： 可以接收多个子类对象

 */
public class Test {

    public static void main(String[] args) {
        //1.接收HR的要求
        Scanner in = new Scanner(System.in);
        System.out.println("请问您需要哪一个国家的程序员");
        char c = in.next().charAt(0);  // R  J  C
        //2.生产程序员
        Programmer programmer = GuiGuSchool.produceProgrammer(c);//new China();

        //3.进行校验
        if(programmer!=null){
            programmer.eat();
        }else{
            System.out.println("还没有此国家业务");
        }

    }
}
