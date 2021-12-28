package day06Array.src;

import java.util.Arrays;

/*
二维数组的初始化：
静态初始化
    数据类型[][] 数组名 = {{值...},{值...}};声明和赋值不可以分开

     数据类型[][] 数组名 = new 数据类型[][]{{值...},{值...}};  声明和赋值可以分开

动态初始化：
    数据类型[][] 数组名 = new 数据类型[值1][值2];
        值1： 代表二维数组内一维数组的个数
        值2： 代表一维数组的长度


    数据类型[][] 数组名 = new 数据类型[值1][];
       值1： 代表二维数组内一维数组的个数

注意：
    1.二维数组的长度数组内一维数组的个数
    2.数组名[下标1][下标2]
        下标1： 一维数组的下标
        下标2：一维数组内元素的下标

 */
public class DoubleArray3 {
    public static void main(String[] args) {
        int[][] arr = new int[3][5];
        arr[0][0]=100;
        arr[2][4]=666;
        System.out.println("arr.length = " + arr.length);//3
        System.out.println("arr[0].length = " + arr[0].length);
        System.out.println("Arrays.toString(arr[0]) = " + Arrays.toString(arr[0]));
        System.out.println("Arrays.toString(arr[1]) = " + Arrays.toString(arr[1]));
        System.out.println("Arrays.toString(arr[2]) = " + Arrays.toString(arr[2]));


        double[][]dArr = new double[3][];
      //  dArr[0]={3.14,6.28};
        dArr[0]=new double[]{3.14,6.28};
        dArr[1]=new double[3];
        dArr[2]=new double[]{1,3,5,6};

        System.out.println("Arrays.toString(dArr[0]) = " + Arrays.toString(dArr[0]));
        System.out.println("Arrays.toString(dArr[1]) = " + Arrays.toString(dArr[1]));
        System.out.println("Arrays.toString(dArr[2]) = " + Arrays.toString(dArr[2]));

     /*   System.out.println("dArr[0] = " + dArr[0]);

        System.out.println("dArr[1][1] = " + dArr[1][1]);*/

    }
}
