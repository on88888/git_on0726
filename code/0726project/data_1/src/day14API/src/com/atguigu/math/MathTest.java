package day14API.src.com.atguigu.math;

import org.junit.Test;
/*
Math:数学类


 */
public class MathTest {

    @Test
    public void test01(){
        //绝对值
        System.out.println("Math.abs(-10) = " + Math.abs(-10));
        System.out.println("Math.abs(10) = " + Math.abs(10));
        //开平方
        System.out.println("Math.sqrt(9) = " + Math.sqrt(9));
        System.out.println("Math.sqrt(81) = " + Math.sqrt(81));

        // Math.pow(x, y) x^y
        System.out.println("Math.pow(2, 3) = " + Math.pow(2, 3));
        System.out.println("Math.pow(3, 3) = " + Math.pow(3, 3));

        //向下取整
        System.out.println("Math.floor(9.7) = " + Math.floor(9.7));
        System.out.println("Math.floor(5.9) = " + Math.floor(5.9));

        //向上取整
        System.out.println("Math.ceil(3.1) = " + Math.ceil(3.1));
        System.out.println("Math.ceil(8.3) = " + Math.ceil(8.3));

        //四舍五入
        System.out.println("Math.round(3.6) = " + Math.round(3.6));
        System.out.println("Math.round(4.5) = " + Math.round(4.5));
        System.out.println("Math.round(9.3) = " + Math.round(9.3));


    }

    @Test
    public void test02(){

        //获取最大值
        System.out.println("Math.max(10, 20) = " + Math.max(10, 20));
        System.out.println("Math.max(30, 10) = " + Math.max(30, 10));

        //获取最小值
        System.out.println("Math.min(30, 50) = " + Math.min(30, 50));
        System.out.println("Math.min(3.14, 9.67) = " + Math.min(3.14, 9.67));


        System.out.println("Math.PI = " + Math.PI);
        System.out.println("Math.E = " + Math.E);


    }

    @Test
    public void test03(){

        // 33 ~55

        for (int i = 0; i < 100; i++) {

            int i1 = (int) (Math.random() * (55 - 33 + 1) + 33);

            System.out.println(i1);
        }



    }
}
