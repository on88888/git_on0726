package day14API.src.com.atguigu.math;

import org.junit.Test;

import java.util.Random;

public class RandomTest {
    @Test
    public void test04(){
        //种子数相同  随机数是相同的
        Random random = new Random(1);

        for (int i = 0; i < 10; i++) {
            int i1 = random.nextInt(20);
            System.out.print(i1+"  ");//5  8  7  13  14  4  14  6  18  8
        }
    }


    @Test
    public void test03(){

        Random random = new Random(1);

        for (int i = 0; i < 10; i++) {
            int i1 = random.nextInt(20);
            System.out.print(i1+"  ");//5  8  7  13  14  4  14  6  18  8

        }

    }





    @Test
    public void test02(){
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            //随机生成boolean值
            boolean b = random.nextBoolean();

            System.out.println("b = " + b);

        }
    }

    @Test
    public void test01(){

        //1.创建Random对象

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            //返回int 范围内的随机数
            //int i1 = random.nextInt();
            //[0,n)
            int i1 = random.nextInt(10);
            System.out.println("random.nextInt() = " +i1);
        }

    }
}
