package day06Array.src;/*
1           0
22          1
333         2
4444        3
55555       4
 */

public class DoubleArrayExer1 {
    public static void main(String[] args) {

        int[][]arr = new int[5][];
        for(int i = 0;i<arr.length;i++){
            //创建一维数组
            arr[i]=new int[i+1];
            //要给一维数组每一个元素赋值 遍历一维数组
            for(int j = 0;j<arr[i].length;j++){
                //给数组元素赋值
                arr[i][j]=i+1;
            }
        }

        for (int[] ints : arr) {
            for (int e : ints) {
                System.out.print( e+" ");
            }
            System.out.println();
        }


    }
}
