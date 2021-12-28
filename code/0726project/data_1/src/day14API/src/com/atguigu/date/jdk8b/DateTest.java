package day14API.src.com.atguigu.date.jdk8b;

import org.junit.Test;

import java.util.Date;

public class DateTest {

    @Test
    public void test03(){
        //将毫秒数转为时间
        Date date = new Date(1628925028479L);

        System.out.println(date);



    }
    @Test
    public void test02(){
        Date date = new Date();
        int year = date.getYear();//返回距离1900年的整年数
        System.out.println("year = " + year);

        int month = date.getMonth();//月份从0开始
        System.out.println("month = " + month);

        //将当前时间转为毫秒数
        long time = date.getTime();//1628925028479
        System.out.println("time = " + time);


    }

    @Test
    public void test01(){
        Date date = new Date();
        System.out.println("date = " + date);//Sat Aug 14 15:05:27 CST 2021

        String s = date.toLocaleString();//2021-8-14 15:06:45

        System.out.println("s = " + s);
    }
}
