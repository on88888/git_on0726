package day05Array.src;

import java.util.Arrays;

public class Sort3 {

    public static void main(String[] args) {

        int[] arr = {10,20, 50, 40, 30, 60, 70};

        System.out.println("排序前  = " + Arrays.toString(arr)+"\n");

        //-1  确定排序的次数
        for (int i = 0; i < arr.length - 1; i++) {
            //定义变量 假设此次排序数组已经有序
            boolean flag  =true;
            for (int j = 0; j < arr.length-1-i; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //如果有前一个数>后一个数的情况 则 证明 数组无序
                    flag=false;
                }

            }

            if(flag){
                break;
            }
            System.out.println("排序中  = " + Arrays.toString(arr));
        }
        System.out.println("\n排序后  = " + Arrays.toString(arr));
    }
}
