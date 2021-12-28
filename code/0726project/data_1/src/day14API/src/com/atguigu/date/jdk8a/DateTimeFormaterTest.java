package day14API.src.com.atguigu.date.jdk8a;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/*
jdk8After 日期格式化

1.1 日期---> 字符串
    预定义格式化
    本地格式化
    自定义格式化


1.2 字符串--->日期

    必须采用24小时制

 */
public class DateTimeFormaterTest {

    @Test
    public void test04() {
        String s = "2021-08-14 16:55:46";
        //1.创建日期格式化对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        //2.日期类.parese(字符串,格式化对象);
        LocalDateTime time = LocalDateTime.parse(s,formatter);
        System.out.println("time = " + time);
    }


    @Test
    public void test03() {
        //   自定义格式化

        //1.获取日期对象
        LocalDateTime now = LocalDateTime.now();
        //2.获取日期格式化对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss E");//2021-08-14 16:55:46 星期六


        //3.日期格式化对象 对日期对象进行格式化 ---> String
        String s = formatter.format(now);

        System.out.println("s = " + s);


    }


    @Test
    public void test02() {
        //本地格式化

        LocalDateTime now = LocalDateTime.now();

        //创建日期格式化对象

        //DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);//21-8-14 下午4:51
        // DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);//2021-8-14 16:51:43
        // DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);//2021年8月14日 下午04时52分04秒
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.systemDefault());// 2021年8月14日 星期六 下午04时53分02秒 CT

        //3.对日期对象进行格式化 转为字符串
        String format = formatter.format(now);

        System.out.println("format = " + format);


    }

    @Test
    public void test01() {
        //预定义格式化
        //日期对象
        LocalDateTime now = LocalDateTime.now();

        //2.准备日期格式化对象
        // DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;// 2021-08-14
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;// 2021-08-14T16:49:13.958

        //3.对日期对象进行格式化 转为字符串
        String s = formatter.format(now);

        System.out.println("s = " + s);

    }
}
