package day06Array.src;

import java.util.Arrays;

public class ArrayTest3 {
    public static void main(String[] args) {
        int [] arr = {10,20,30,40,50,60,70};
        System.out.println("交换前 = " + Arrays.toString(arr));
      //  int endIndex = arr.length-1;
        /*for(int startIndex = 0;startIndex<endIndex;startIndex++){

            int temp = arr[startIndex];
            arr[startIndex]=arr[endIndex];
            arr[endIndex]=temp;
            //结束下标 要向前移动
            endIndex--;
        }*/
        for(int startIndex = 0,endIndex=arr.length-1;startIndex<endIndex;startIndex++,endIndex--){
            int temp = arr[startIndex];
            arr[startIndex]=arr[endIndex];
            arr[endIndex]=temp;
        }



        System.out.println("交换后 = " + Arrays.toString(arr));


    }

}
