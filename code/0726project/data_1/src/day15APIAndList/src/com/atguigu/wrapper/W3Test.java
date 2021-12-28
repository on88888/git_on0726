package day15APIAndList.src.com.atguigu.wrapper;

import org.junit.Test;
/*
包装类型比基本数据类型多了很多的方法
 */
public class W3Test {

    @Test
    public void test04(){
        int m = 10;
        int n = 20;
        //比较两个int值谁大
        int compare = Integer.compare(m, n);
        System.out.println("compare = " + compare);
    }

    @Test
    public void test03(){

        int max = Integer.max(20, 30);
        System.out.println("max = " + max);

        System.out.println("Integer.min(10, 60) = " + Integer.min(10, 60));


    }


    @Test
    public void test02(){
            //二进制转为10进制
        System.out.println(0B101);//5
        //8进制转为10进制
        System.out.println(011);//9
        //16进制转为10进制
        System.out.println(0x11);

        //将10进制数转为二进制
        String toBinaryString = Integer.toBinaryString(5);
        System.out.println("toBinaryString = " + toBinaryString);
        //将10进制数转为8进制
        String toOctalString = Integer.toOctalString(9);

        System.out.println("toOctalString = " + toOctalString);
        //将10进制数转为 16 进制
        System.out.println("Integer.toHexString(11) = " + Integer.toHexString(11));

    }


    @Test
    public void test01(){
        //获取int的最大值
        int maxValue = Integer.MAX_VALUE;

        System.out.println("maxValue = " + maxValue);
//获取int的最小值
        int minValue = Integer.MIN_VALUE;

        System.out.println("minValue = " + minValue);


    }
}
