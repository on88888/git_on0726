package day14API.src.com.atguigu.big;

import org.junit.Test;

import java.math.BigInteger;

/*
BigInteger: 表示过大的整数
 */
public class BigIntegerTest {

    @Test
    public void test01() {

        long l = Long.MAX_VALUE;

        System.out.println("l = " + l);//922 3372 0368 5477 5807

        long l1 = 9223372036854775807L;

        int m = 100;
        m = m - 20;

    }

    @Test
    public void test02() {
        BigInteger b1 = new BigInteger("9223372036854775808999");
        System.out.println("b1 = " + b1);
        BigInteger b2 = new BigInteger("100");
        BigInteger b3 = new BigInteger("50");
        //加法
        BigInteger add = b2.add(b3);
        System.out.println("add = " + add);
        //减法
        BigInteger subtract = b2.subtract(b3);
        System.out.println("subtract = " + subtract);
        //乘法
        BigInteger multiply = b2.multiply(b3);
        System.out.println("multiply = " + multiply);
        //除法
        BigInteger divide = b2.divide(b3);
        System.out.println("divide = " + divide);
        //取模
        BigInteger remainder = b2.remainder(b3);
        System.out.println("remainder = " + remainder);
    }
    @Test
    public void test03() {

        BigInteger big = new BigInteger("10");
        //将BigInteger ---> int
        int i = big.intValue();
        System.out.println("i = " + i);
        //将BigInteger ---> long
        long l = big.longValue();

    }
}
