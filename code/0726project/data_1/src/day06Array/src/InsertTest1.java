package day06Array.src;

import java.util.Arrays;

/*

不考虑扩容的情况
数组元素插入：
    1.末端插入


    2.非末端插入


 */
public class InsertTest1 {

    public static void main(String[] args) {

        String [] sArr = {"张三","李四","王五","赵六",null};
        sArr[4]="安琪拉";

        System.out.println("Arrays.toString(sArr) = " + Arrays.toString(sArr));
    }
}
