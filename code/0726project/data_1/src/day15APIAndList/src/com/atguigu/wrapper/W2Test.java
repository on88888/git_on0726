package day15APIAndList.src.com.atguigu.wrapper;

import org.junit.Test;
/*
 基本数据类型 ----> 包装类型 ： 装箱
    int ---> Integer
    自动装箱： 将基本数据类型 ---> 包装类型

 包装类型----> 基本数据类型： 拆箱
     自动装箱：将包装类型---> 基本数据类型



 */
public class W2Test {

    @Test
    public void test02(){

        Integer i1 = new Integer(20);
        //将Integer 直接转为 int  自动拆箱
        int m = i1;
        Double aDouble = new Double(3.14);
        //将Double 直接转为 double  自动拆箱
        double d = aDouble;

    }



    @Test
    public void test01(){
        //基本数据类型 ----> 包装类型 ： 装箱
        int m = 10;


        //通过构造器
        Integer i1 = new Integer(m);
        //通过包装类的方法
        Integer i2 = Integer.valueOf(m);
        //自动装箱
        Integer i3 = m;
        System.out.println("i3 = " + i3);


        double d = 3.14;

        Double d1 = d;


        long l = 12L;

        Long l1 = l;

    }
}
