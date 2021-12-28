package day08OOP.src.com.atguigu.argments;

import java.util.Arrays;

/*
可变参数/形参： 当实参的类型确定 个数不确定时,可以使用可变形参进行接收

注意：
    1.可变参数 使用数组进行数据存储
    2.可以接收的实参的个数是[0,n]
    3.可变形参必须位于参数列表的最后
    4.一个方法只能有一个可变形参
 */
public class Test {
    public static void main(String[] args) {
/*
        sum3(10,20);
        sum3(10,20,30,40);
        sum3(10,20,30,40,60,70,80,90,50);*/
       /* sum3();
        int[] arr = {1,2,3};

        sum3(arr);*/

        sum3(3.14,10,20,30);

    }

    public static void sum3(int...a){
        System.out.println(a.length);
    }

    public static void  sum3(double d,int...a){

        System.out.println(d);

        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));


    }


    /*private static void sum3(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
    }
*/
   /* private static void sum3(int i, int i1, int i2, int i3) {

    }*/

   /* private static void sum3(int i, int i1) {
        System.out.println(i+i1);
    }*/
}
