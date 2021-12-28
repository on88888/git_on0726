package day12OOPAndException.src.com.atguigu.comparator;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {


        int[] arr = {10,7,6,3,9};

        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}
