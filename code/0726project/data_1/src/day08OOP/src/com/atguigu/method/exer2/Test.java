package day08OOP.src.com.atguigu.method.exer2;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {



        int[] arr = {10,20,30,40,90,100};
        int[] arr1 = {40,90,100};
        String s = ArraysTools.toString(arr1);

        System.out.println("s = " + s);


        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}
