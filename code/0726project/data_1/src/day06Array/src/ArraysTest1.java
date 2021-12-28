package day06Array.src;

import java.util.Arrays;

public class ArraysTest1 {
    public static void main(String[] args) {

        int[] arr = {10,20,30};
        String s = Arrays.toString(arr);
        System.out.println("s = " + s);

        int[] arr1 = new int[6];

        System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
        //批量添加数据
        // Arrays.fill(arr1, 66);
        //Arrays.fill(数组名, 开始下标, 结束下标, 填充的值); [开始下标, 结束下标)
        Arrays.fill(arr1, 2, 5, 99);
        System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));


        String [] sArr  ={"李白","杜甫","白居易"};
/*
        String[] copyOf = Arrays.copyOf(sArr, sArr.length*2);

        sArr=copyOf;*/
            //复制数组元素 创建一个新数组
          sArr = Arrays.copyOf(sArr, sArr.length*2);

        System.out.println("Arrays.toString(copyOf) = " + Arrays.toString(sArr));
        //按照范围复制
        String[] range = Arrays.copyOfRange(sArr, 1, 2);
        System.out.println("Arrays.toString(range) = " + Arrays.toString(range));




    }
}
