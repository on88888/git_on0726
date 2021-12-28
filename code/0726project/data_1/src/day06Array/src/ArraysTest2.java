package day06Array.src;

import java.util.Arrays;
public class ArraysTest2 {
    public static void main(String[] args) {
        int[] arr = {70, 50, 60, 40, 20, 30, 10};
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        //排序
        Arrays.sort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        /*
        二分查找：
            1.数组有序
            2.如果没有此元素 会返回负数
         */
        int index = Arrays.binarySearch(arr, 700);
        System.out.println("index = " + index);
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 3, 2};
        //== 比较数组时 比较的是地址值
        System.out.println(arr1 == arr2);
        //比较数组内容  按照下标顺序 挨个比较
        boolean b = Arrays.equals(arr1, arr2);
        System.out.println("b = " + b);
    }
}
