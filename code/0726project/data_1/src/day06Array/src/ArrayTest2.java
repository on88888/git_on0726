package day06Array.src;

public class ArrayTest2 {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70};


        System.out.println("交换前："+ java.util.Arrays.toString(arr));

        //由图可知  交换次数
        for(int i = 0;i<arr.length/2;i++){
            //首尾互换
           int temp = arr[i];
           arr[i]=arr[arr.length-1-i];
           arr[arr.length-1-i]=temp;
        }
        System.out.println("\n交换后："+ java.util.Arrays.toString(arr));



    }
}
