package day07.Test08;

public class CommonsTools {
	static String getWeekName(int week) {
		switch (week) {
			case 1:
				return "Monday";
			case 2:
				return "Tuesday";
			case 3:
				return "Wednesday";
			case 4:
				return "Thursday";
			case 5:
				return "Friday";
			case 6:
				return "Saturday";
			case 7:
				return "Sunday";
		}
		return "";
	}
		/*return switch (week) {
			case 1 -> "Monday";
			case 2 -> "Tuesday";
			case 3 -> "Wednesday";
			case 4 -> "Thursday";
			case 5 -> "Friday";
			case 6 -> "Saturday";
			case 7 -> "Sunday";
			default -> "";
		};*/

	static String getMonthName(int month){
		String[] arr = {"January","February","March","April","May","June","July","August","September","October","November","December"};
			if(month >= 1 && month <= 12){
				return arr[month-1];
			}
			return "";
		}

	static int getTotalDaysOfMonth(int year, int month){
		int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
		if(isLeapYear(year)){
				days[1]++;//闰年2月29天
			}
			if(month >= 1 && month <= 12){
				return days[month-1];
			}
			return 0;
		}

	static int getTotalDaysOfYear(int year){
		if(isLeapYear(year)){
			return 366;
		}
		return 365;
	}

	static boolean isLeapYear(int year){
		return year%4==0 && year%100!=0 || year%400==0;
	}

}



