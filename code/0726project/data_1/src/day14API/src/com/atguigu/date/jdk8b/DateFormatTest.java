package day14API.src.com.atguigu.date.jdk8b;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
日期格式化类：DateFormat 抽象类
    1. 日期 ----> 字符串
        日期格式化对象.format();

    2.字符串 ---> 日期
         日期格式化对象.parse();

 */
public class DateFormatTest {

    @Test
    public void test03() throws ParseException {
        //date.getTime();
        //键盘输入生日： 2000-9-9
        System.out.println("请输入您的出生日期(1999-09-12)");
        Scanner in = new Scanner(System.in);
        String birthday = in.next();//2000-9-9

        //将字符串转为 日期对象
        //创建日期格式化对象
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date date = df.parse(birthday);
        //获取毫秒值
        long birthdayTime = date.getTime();

        //获取当前日期对象
        Date now = new Date();
        // 获取毫秒值
        long nowTime = now.getTime();

        //两个毫秒值做减法

        long days = (nowTime - birthdayTime) / 1000 / 60 / 60 / 24;

        System.out.println("days = " + days);


    }


    @Test
    public void test01(){

       // DateFormat df = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
        //创建日期格式化对象
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日  hh时mm分ss秒");//2021年08月14日  15时33分01秒

        //创建日期对象
        Date now = new Date();
        System.out.println("now = " + now);
        //对日期进行格式化转为字符串
        String format = df.format(now);
        System.out.println("format = " + format);
    }

    @Test
    public void test02() throws ParseException {
        String s = "2020-2-3 13:34:56";

        //1.创建日期格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //2.parse()

        Date date = sdf.parse(s);
        System.out.println(date);


    }

}
