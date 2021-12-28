package day09OOP.src.com.atguigu.rescursion.r1;
/*
递归： 方法自己调用自己
     直接递归
     间接递归

     使用递归时   递归需要有出口
                 不断的向出口靠近
                  可能出现 StackOverflowError
求1~100的和
        循环
        递归
 */
public class Test {
    //100 + 99 + 98 +97 .... + 1
    public static int getSum(int num){

        if(num==1){
            return 1;
        }
        return num+getSum(num-1);

    }

    public static void main(String[] args) {

        int sum = getSum(1000000);

        System.out.println("sum = " + sum);
  /*      int sum = 0;

        for (int i = 1; i <101 ; i++) {

            sum+=i;

        }
        System.out.println("sum = " + sum);*/
    }
}
