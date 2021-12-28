package day05Array.src;

import java.util.Arrays;
public class Sort2 {
    public static void main(String[] args) {

        int[] arr = {70, 60, 50, 40, 30, 20, 10};

        System.out.println("排序前  = " + Arrays.toString(arr)+"\n");

        //-1  确定排序的次数
        for (int i = 0; i < arr.length - 1; i++) {
            //每一次排序
            //-1 防止出现 数组下标越界异常
            //-i 专注于无序部分  不需要再比较有序部分的值
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("排序中  = " + Arrays.toString(arr));
        }
        System.out.println("\n排序后  = " + Arrays.toString(arr));
    }
}
