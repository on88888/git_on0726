package day06Array.src;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {


        int[] arr = {70,40,30,10,20,9};

        System.out.println("排序前 = " + Arrays.toString(arr));


        //i: 就是指定位置
        //i=0: 第一小的值存放的位置
        for(int i = 0;i<arr.length;i++){
            //定义假设的最小值下标
            int minIndex = i;
            for(int j = i+1;j<arr.length;j++){
                //让数组元素与假设的最小值下标对应的值进行比较
                if(arr[j]<arr[minIndex]){
                    //记录真正的最小值下标
                    minIndex=j;
                }
            }
            //获取真正的最小值下标后 进行位置交换
            //把真正的最小值 与 指定位置的值进行交换
            int temp = arr[minIndex];
            arr[minIndex]=arr[i];
            arr[i]=temp;
        }

        System.out.println("排序后 = " + Arrays.toString(arr));
    }
}
