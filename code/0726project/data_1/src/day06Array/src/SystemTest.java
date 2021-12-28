package day06Array.src;

import java.util.Arrays;

public class SystemTest {
    public static void main(String[] args) {
        // System.out.println(999);

        int[] arr = {10, 20, 30, 40};

        int[] newArr = new int[arr.length * 2];
        System.out.println("复制前 = " + Arrays.toString(newArr));

        /*
         * @param      src      源数组
         * @param      srcPos   源数组开始下标
         * @param      dest     目标数组
         * @param      destPos  目标数组开始下标
         * @param      length   复制的数量

          System.arraycopy(src,srcPos,dest,destPos,lenth);
          将 arr 数组内 从下标为0开始复制  粘贴到 数组 newArr中 从下标为0的位置开始粘贴 一共粘贴 4个

         System.arraycopy(arr,0,newArr,0,arr.length);
         */
        // System.arraycopy(arr,0,newArr,0,arr.length);
        System.arraycopy(arr, 0, newArr, 3, arr.length);

        System.out.println("复制后 = " + Arrays.toString(newArr));

        String[] strArr = {"盖伦", "亚缩", "诺手", "盲僧"};

        String [] newStrArr = new String[strArr.length*2];

        System.arraycopy(strArr,1,newStrArr,2, 3);

        System.out.println("Arrays.toString(newStrArr) = " + Arrays.toString(newStrArr));


    }
}
