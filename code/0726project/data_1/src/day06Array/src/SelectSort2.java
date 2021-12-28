package day06Array.src;

import java.util.Arrays;

public class SelectSort2 {
    public static void main(String[] args) {
    /*
    1,2,40,30,10,20,70  0
     */
        int[] arr = {1,2,40,30,10,20,70};
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
            //如果真正的最小值下标 与放入的位置下标 不一样 才进行位置交换
            if(i!=minIndex){
                int temp = arr[minIndex];
                arr[minIndex]=arr[i];
                arr[i]=temp;
            }
        }
        System.out.println("排序后 = " + Arrays.toString(arr));
    }
}
