package day07.Test08;

public class Test8 {
	public static void main(String[] args) {
		System.out.println("3月：" + CommonsTools.getMonthName(3));
		System.out.println("周三：" + CommonsTools.getWeekName(3));
		System.out.println("2019-2的总天数：" + CommonsTools.getTotalDaysOfMonth(2019, 2));
		System.out.println("2022年是否是闰年？" + CommonsTools.isLeapYear(2022) );
		System.out.println("2022年的总天数：" + CommonsTools.getTotalDaysOfYear(2022));
	}
}
