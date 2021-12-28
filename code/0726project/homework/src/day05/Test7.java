package day05;

import java.util.Arrays;

public class Test7 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,3,2,1};
        int[] arr1 = {1,2,3,5,3,2,1};
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if(arr.length!=arr1.length){
                System.out.println("数组长度不一致");
                flag = false;
                break;
            }
            if(arr[i]!=arr1[i]){
                System.out.println("数组有元素不一致");
                flag = false;
                break;
            }
        }
        System.out.println(Arrays.toString(arr)+"\n"+Arrays.toString(arr1));
        System.out.println("是否一致："+flag);
    }
}
