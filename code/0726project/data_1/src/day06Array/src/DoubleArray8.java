package day06Array.src;

public class DoubleArray8 {
    public static void main(String[] args) {

        int[][] arr = new int[3][];

        arr[0]=new int[]{66,99};

        arr[1]=new int[3];

        arr[1][1]=33;


        System.out.println("arr[2] = " + arr[2]);
        //只要用null 调用任何资源 NullPointerException
        //System.out.println(arr[2].length);

    }
}
