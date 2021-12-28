package day05Array.src;

public class ArrayTest3 {
/*
1. 查找指定元素的下标
        有输出下标
        没有输出查无此元素

2.求最值
     2.1假设一个最大值

     2.2 让数组元素与最大值进行比较  如果比最大值还大 说明数组元素是最大值
 */
    public static void main(String[] args) {
        int[] arr = {10,20,30,40,9,3,1};
        //定义变量 记录总和
        int sum = 0;
        //定义变量记录偶数的个数
        int count = 0;
        for (int i : arr) {
            //判断为偶数
            if(i%2==0){
                count++;
            }
            //求总和
            sum+=i;
        }

        System.out.println("总和是 ："+ sum+",平均数是："+ sum/arr.length+"\n偶数有： "+count+" 个");
    }
}
