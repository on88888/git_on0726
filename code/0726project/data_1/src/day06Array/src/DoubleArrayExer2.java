package day06Array.src;

public class DoubleArrayExer2 {
    public static void main(String[] args) {
        int[][]arr = {{10,200},{1,2},{90,67}};

        //假设最大值为第一个元素
        int maxNum = arr[0][0];

        int minNum  =arr[0][0];

        for (int[] ints : arr) {
            for (int i : ints) {
                //数组元素比最大值还大 说明 数组元素是最大值
                if(i>maxNum){
                    maxNum=i;
                }

                //数组元素比最小值还小说明 数组元素是最小值
                if(i<minNum){
                    minNum=i;
                }
            }
        }

        System.out.println("maxNum = " + maxNum);
        System.out.println("minNum = " + minNum);


    }
}
