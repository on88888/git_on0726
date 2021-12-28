package day14API.src.com.atguigu.date.jdk8b;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {
/*
Calendar:是一个抽象类
 */
    @Test
    public void test01(){
        Calendar c;
        //创建Calendar子类对象
        GregorianCalendar g = new GregorianCalendar();

        System.out.println(g);

        //通过静态方法 获取Calendar对象
        Calendar instance = Calendar.getInstance();

        System.out.println("instance = " + instance);

    }

    @Test
    public void test02(){

        Calendar instance = Calendar.getInstance();

        int year = instance.get(Calendar.YEAR);
        System.out.println("year = " + year);

        int month = instance.get(Calendar.MONTH);//月份从0开始
        System.out.println("month = " + month);


    }

    @Test
    public void test03(){

        Calendar instance = Calendar.getInstance();

        int year = instance.get(Calendar.YEAR);
        System.out.println("year = " + year);//2021
        //设置当前时间的年份
        instance.set(Calendar.YEAR, 2000);
        year = instance.get(Calendar.YEAR);
        System.out.println("year = " + year);




    }

    @Test
    public void test04(){
        Calendar instance = Calendar.getInstance();
        //获取几号
        System.out.println("instance.get(Calendar.DATE) = " + instance.get(Calendar.DATE));
        //将当前日期增加两年
        instance.add(Calendar.YEAR, 2);

        System.out.println("instance.get(Calendar.YEAR) = " + instance.get(Calendar.YEAR));

        instance.add(Calendar.DATE, -2);
        System.out.println("instance.get(Calendar.DATE) = " + instance.get(Calendar.DATE));
        System.out.println("instance.get(Calendar.MONTH) = " + instance.get(Calendar.MONTH));
    }

}
