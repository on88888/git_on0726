package day07OOP.src.com.atguigu.method.m2;
/*
一. 定义一个方法  5  3
                 6  9
                 2  10
******
******
******

二.定义一个方法 接收一个整数 判断该数是否为质数

 */
public class Test2 {
    public static void show(int rows,int cols){

        for(int i = 1;i<=rows;i++){
            for (int j = 1;j<=cols;j++){
                System.out.print("*");
            }
            System.out.println();
        }

    }


    public static void main(String[] args) {

        show(3, 7);
        System.out.println();
        show(2, 10);


    }
}
