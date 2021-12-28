package day08OOP.src.com.atguigu.argments;
/*
求最大公约数
     4  6  10 ---> 2


     3  5 --->  1

     3 6 9 -->  3

定义方法 返回 输入数字的最大公约数

1.获取数组元素的最小值

2.让最小值与每一个数组元素进行整除
    一旦有一个不能整除 说明此数不是最大公约数 最小值-1
 */
public class Test4 {
    public static void main(String[] args) {

        int maxYueShu = getMaxYueShu(4, 6, 10);
        System.out.println("maxYueShu = " + maxYueShu);

        System.out.println("getMaxYueShu(6,12,9) = " + getMaxYueShu(6, 12, 9));
    }

    /**
     *  求最大公约数
     * @param nums 数组
     * @return 最大公约数
     */
    public static int getMaxYueShu(int...nums){//4 6 10
        //1.求最小值
        int minNum = nums[0];
        for (int num : nums) {
            if(num<minNum){
                minNum=num;
            }
        }

        //2.让最小值与每一个元素整除
      l:  for(int i = minNum;i>=1;i--){
            for (int num : nums) {
                if(num%i!=0){
                    continue l;
                }
            }
            return i;
        }
        return 1;
    }


}
