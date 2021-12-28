package day08OOP.src.com.atguigu.review;

/*
1.新建一个带返回值的方法 判断数字是否为质数 是返回true  不是返回false

2.借助此方法输出 2~100内所有的质数

 */
public class Test {

    public static void main(String[] args) {

      /*  System.out.println("isZhiShu(9) = " + isZhiShu(9));
        System.out.println("isZhiShu(11) = " + isZhiShu(11));*/

        for (int i = 2; i <= 100; i++) {

            boolean b = isZhiShu(i);

            if (b) {
                System.out.println(i);
            }
        }

    }

    public static boolean isZhiShu(int num) {
        for (int i = 2; i < num; i++) {
            //除了 1和本身还有其他约数 不是质数
            if (num % i == 0) {
                return false;
            }
        }
        //除了1和本身之外 所有的数都不能整除  说明 此数 是质数
        return true;
    }
}
