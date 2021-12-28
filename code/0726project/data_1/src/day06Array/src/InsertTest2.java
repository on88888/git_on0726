package day06Array.src;

import java.util.Arrays;

public class InsertTest2 {
    public static void main(String[] args) {
                        // 0      1      2      3     4
        String [] sArr = {"张三","李四","王五","赵六",null};

        //将安琪拉插入到 王五的位置
        //王五的位置
        int insertIndex  = 2;

        for(int i = sArr.length-1;i>=insertIndex;i--){
            sArr[i]=sArr[i-1];
        }
        System.out.println("数组移动 = " + Arrays.toString(sArr));
        //将指定元素放入到 指定的位置
        sArr[insertIndex]="安琪拉";

        System.out.println(Arrays.toString(sArr));

    }
}
