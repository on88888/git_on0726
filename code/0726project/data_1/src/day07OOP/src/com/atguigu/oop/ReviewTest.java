package day07OOP.src.com.atguigu.oop;

import java.util.Arrays;

public class ReviewTest {
    public static void main(String[] args) {

        /*
        创建长度为10的数组
        生成 20~29范围内的随机数 将数字放到数组内
        数字不能重复
         */
        int[]arr = new int[10];
        int num = 0;
        for (int i = 0; i < arr.length; i++) {

         l:   while (true){
             // 假设产生随机数是不重复的
               boolean flag = false;
                num = (int)(Math.random()*(29-20+1)+20);
                //存储数据前 校验 随机数是否在数组内出现过
                for (int j = 0; j < arr.length; j++) {
                    //出现过  再去产生新的随机数
                    if(num==arr[j]){
                        flag=true;
                        continue l;
                    }
                }
                //如果没有 出现过 flag是false
                if(!flag){
                    break ;
                }
            }
            //没有出现过
            arr[i]=num;
        }
        System.out.println(Arrays.toString(arr));




    }
}
