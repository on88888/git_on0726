package day07.Test4;
/*
声明一个日期类MyDate，包含属性：年、月、日，并在MyDate类中声明几个方法：
1、boolean isLeapYear()：判断当前日期的是闰年吗？
2、void set(int y, int m, int d)：修改年，月，日为新日期
3、void puls(int years, int months, int days)：加上years年，months月，days天后的日期
	并在测试类Test04的main方法中创建对象，并调用测试
 */

public class Test4 {
	public static void main(String[] args) {
		MyData my = new MyData();
		my.set(2019, 5, 13);

		System.out.println(my.year + "年" + my.month + "月" + my.day + "日");
		System.out.println("是闰年吗？" + my.isLeapYear());

		my.puls(1, 70, 70);
		System.out.println("再加1年70个月70天之后的日期是：");
		System.out.println(my.year + "年" + my.month + "月" + my.day + "日");
	}
}
