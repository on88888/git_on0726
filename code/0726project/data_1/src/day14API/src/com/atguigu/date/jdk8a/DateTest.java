package day14API.src.com.atguigu.date.jdk8a;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/*
日期间隔： 年  月  天  共月

时间间隔  天 时  分 秒

 */
public class DateTest {


    @Test
    public void test02(){
        //时间间隔  天 时  分 秒
        LocalDateTime  now = LocalDateTime.now();

        LocalDateTime time = LocalDateTime.of(2000, 3, 4, 8, 9);

        //获取时间间隔对象
        Duration duration = Duration.between(time, now);

        //一共相差的天数
        System.out.println("duration.toDays() = " + duration.toDays());
        //一共相差的小时数
        System.out.println("duration.toHours() = " + duration.toHours());

    }



    @Test
    public void test01(){
        //日期间隔： 年  月  天  共月
        LocalDate now = LocalDate.now();

        LocalDate localDate = LocalDate.of(2023, 11, 25);
        // 获取日期间隔的对象         开始时间   结束时间
        Period period = Period.between(now, localDate);

        //相差的年数
        System.out.println("period.getYears() = " + period.getYears());
        //相差的月数

        System.out.println("period.getMonths() = " + period.getMonths());
        //相差的天数
        System.out.println("period.getDays() = " + period.getDays());
        //相差的总月数
        System.out.println("period.toTotalMonths() = " + period.toTotalMonths());
    }

}
