package day06Array.src;

public class ArrayTest1 {

    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40, 50, 60, 70};


        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + "\t");
        }

        System.out.println("\n");
        for (int index = arr.length - 1; index >= 0; index--) {

            System.out.print(arr[index]+"\t");

        }


    }
}
