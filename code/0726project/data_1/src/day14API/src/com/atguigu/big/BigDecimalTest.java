package day14API.src.com.atguigu.big;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
/*
BigDecimal: 表示更加精确的小数

如果是无限小数 必须指定两个条件
        1. 舍入模式
        2. 小数位数

 */

public class BigDecimalTest {


    @Test
    public void test05(){


        BigDecimal b1 = new BigDecimal("10");
        BigDecimal b2 = new BigDecimal("3");

    //    BigDecimal divide = b1.divide(b2);
        //b1.divide(b2, 保留多少位小数, 舍入模式);
        BigDecimal divide = b1.divide(b2, 10, RoundingMode.CEILING);

        System.out.println("divide = " + divide);


    }

    @Test
    public void test04(){


        BigDecimal b1 = new BigDecimal("3.2");

        double v = b1.doubleValue();

        float v1 = b1.floatValue();




    }

    @Test
    public void test03(){

        BigDecimal b1 = new BigDecimal("3.2");

        BigDecimal b2 = new BigDecimal("1.6");

        BigDecimal add = b1.add(b2);
        System.out.println("add = " + add);

        System.out.println("b1.subtract(b2) = " + b1.subtract(b2));

        System.out.println("b1.multiply(b2) = " + b1.multiply(b2));

        System.out.println("b1.divide(b2) = " + b1.divide(b2));

        System.out.println("b1.remainder(b2) = " + b1.remainder(b2));


    }

    @Test
    public void test02(){

        BigDecimal b1 = new BigDecimal("3.1415926543127123212132");
        System.out.println("b1 = " + b1);


    }


    @Test
    public void test01(){

        double d = 3.1415926543127123212132;

        System.out.println("d = " + d);

    }
}
