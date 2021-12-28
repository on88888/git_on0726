package day06Array.src;/*
数组实现扩容
    1.新建一个数组
        扩容到原来的2倍
        扩容到原来的1.5倍

    2.将原有数组的数据 复制到新数组内


    3.将新数组的地址 赋值给原有数组的引用




 */

import java.util.Arrays;

public class ArrayTest4 {
    public static void main(String[] args) {
        int[] arr = {10,20,30};

        System.out.println("arr 数组内容 = " + Arrays.toString(arr));

        //arr[3]=90;

       // System.out.println(arr[3]);
        //创建新数组
     //   int[] newArr = new int[arr.length*2];
        int[] newArr = new int[arr.length+(arr.length>>1)];

        System.out.println(newArr.length);

        //将原有数组的数据 复制到新数组内

        for (int i = 0; i < arr.length; i++) {
            newArr[i]=arr[i];
        }
        //将新数组的地址 赋值给旧数组的引用
        arr = newArr;
        System.out.println("arr 数组内容 = " + Arrays.toString(arr));
    }
}
