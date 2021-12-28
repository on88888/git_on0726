package day06Array.src;

import java.util.Arrays;

public class InsertTest3 {
    public static void main(String[] args) {
                //         0        1    2      3     4
        String [] sArr = {"张三","李四","王五","赵六",null};
        //                 张三","李四","李四""王五","赵六"

        //李四 插入 诺手

        //1.移动数据
     System.arraycopy(sArr, 1, sArr, 2, 3);

        System.out.println("Arrays.toString(sArr) = " + Arrays.toString(sArr));

        //2.将诺手插入到指定的位置

        sArr[1]="诺手";
        System.out.println("Arrays.toString(sArr) = " + Arrays.toString(sArr));
    }
}
