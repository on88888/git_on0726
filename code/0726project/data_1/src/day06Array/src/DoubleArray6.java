package day06Array.src;/*
for(数组元素类型 变量名 ： 数组名)
 */

import java.util.Arrays;

public class DoubleArray6 {
    public static void main(String[] args) {
        String[][]   sArr = new String[][]{{"李白","杜甫"},{"王安石","苏轼"},{"李商隐","白居易","李清照"}};

        for(String[]   ele:sArr){
           // System.out.println(ele);
            for(String e:ele){
                System.out.print(e+"\t");
            }
            System.out.println();
        }

       /* int[] arr = {10,20,30};
        for(int ele:arr){
            System.out.println("ele = " + ele);
        }*/

        System.out.println("Arrays.toString(sArr) = " + Arrays.toString(sArr));
        //展示二维数组的数据
        System.out.println("Arrays.deepToString(sArr) = " + Arrays.deepToString(sArr));

    }
}
