package day09OOP.src.com.atguigu.rescursion.r2;
/*

1 2 3 4 5 6 7  8  9  10

1 1 2 3 5 8 13 21 34 55

第一个位置 1
第二个位置 1

从第三个位置开始 所有的数 都等于 前一个数 + 前两个数

5 = 3 + 2
    3 = 2 + 1
        2 = 1 + 1

   2 = 1 + 1
 */
public class FeiBo {

    /**
     * 求指定位置的斐波那锲数
     * @param position 指定的位置
     * @return 斐波那锲数
     */
    public static int getFeiBoNum(int position){
        //第一个位置 第二个位置 永远是1
        if(position==1 || position==2){
            return 1;
        }
        //如果不是第一个数或者第二个数 = 前一个位置 + 前两个位置
        return getFeiBoNum(position-1)+getFeiBoNum(position-2);
    }


    public static void main(String[] args) {

        int feiBoNum = getFeiBoNum(4);

        System.out.println("feiBoNum = " + feiBoNum);

    }

}
