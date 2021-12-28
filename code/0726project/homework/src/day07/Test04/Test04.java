package day07.Test04;

public class Test04 {
	public static void main(String[] args) {
		MyDate dd = new MyDate();
		dd.year = 2008;
		dd.month = 8;
		dd.day = 26;
		System.out.println(dd.year+" 年 " + dd.month+ " 月" + dd.day+" 日 ");


		System.out.println("是闰年吗？ "+dd.isLeapYear());

		dd.set(2021,8,5);
		System.out.println(dd.year+" 年 " + dd.month+ " 月" + dd.day+" 日 ");

		dd.puls(3,6,24);
		System.out.println(dd.year+" 年 " + dd.month+ " 月" + dd.day+" 日 ");

	}
}
