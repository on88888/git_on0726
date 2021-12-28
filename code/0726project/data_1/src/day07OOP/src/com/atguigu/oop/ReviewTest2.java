package day07OOP.src.com.atguigu.oop;

import java.util.Arrays;

public class ReviewTest2 {
    public static void main(String[] args) {

        int[] arr = new int[10];

        arr[1]=10;
        arr[1]=90;
/*
23

21  23  0 0 0 0 0 0 0


 */
     l:   for (int i = 0; i < arr.length; i++) {
            //产生随机数
            int num = (int)(Math.random()*(29-20+1)+20);
            for (int j = 0; j < arr.length; j++) {
                if(num==arr[j]){
                    //如果数据重复 让 下标-1 维持在当前位置不变
                    i--;
                    continue l;
                }
            }
            arr[i]=num;
        }

        System.out.println(Arrays.toString(arr));
    }
}
