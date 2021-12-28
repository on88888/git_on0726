package day14API.src.com.atguigu.date.jdk8a;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalTest {
    @Test
    public void test07(){
       // LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = LocalDate.of(2000, 2, 6);
        //判断是否为闰年 LocalDate 调用
        boolean leapYear = localDate.isLeapYear();
        System.out.println("leapYear = " + leapYear);
    }

    @Test
    public void test06(){
        LocalDateTime now = LocalDateTime.now();

        System.out.println("now.getYear() = " + now.getYear());

        System.out.println("now.getMonth() = " + now.getMonth());//AUGUST
        //月份从1开始
        int monthValue = now.getMonthValue();
        System.out.println("monthValue = " + monthValue);

    }


    @Test
    public void test05(){
        LocalDateTime now = LocalDateTime.now();
        //将当前日期的年份设置为指定的日期
        LocalDateTime withYear = now.withYear(2000);

        System.out.println("withYear = " + withYear);

        //
        LocalDateTime of = LocalDateTime.of(2020, 2, 2, 2, 2);
//TemporalAdjuster 将当前日期设置为指定日期
        LocalDateTime with = now.with(of);


        System.out.println("with = " + with);


    }


    @Test
    public void test04(){
        //对日期修改后会生成新的日期对象 不会对原有日期造成影响
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime minusDays = now.minusDays(3);
        System.out.println("minusDays = " + minusDays);
    }

    @Test
    public void test03(){
        //对日期修改后会生成新的日期对象 不会对原有日期造成影响
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);//2021-08-14T16:16:48.167
        //新增年份
        LocalDateTime plusYears = now.plusYears(2);

        System.out.println("plusYears = " + plusYears);
        //新增天数
        LocalDateTime plusDays = now.plusDays(-3);
        System.out.println("plusDays = " + plusDays);

        System.out.println("now = " + now);


    }


    @Test
    public void test02(){
        //获取指定的时间
        LocalDateTime localDateTime = LocalDateTime.of(2020, 3, 4, 5, 6, 8);

        System.out.println("localDateTime = " + localDateTime);


    }

    @Test
    public void test01(){
        //获取当前时间
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);

        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);


    }
}
