package day15APIAndList.src.com.atguigu.enum1.exer1;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("请您输入月份值[1 ~ 12]");
        int value = in.nextInt();

        //调用方法获取枚举对象
        Month month = Month.getByValue(value);

        System.out.println("month = " + month);



    }
}
