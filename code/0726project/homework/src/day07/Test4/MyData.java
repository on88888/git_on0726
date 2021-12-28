package day07.Test4;

public class MyData {
	int year;
	int month;
	int day;

	boolean isLeapYear(){
		return year%4==0 && year%100!=0 || year%400==0;
	}

	void set(int y, int m, int d){
		year = y;
		month = m;
		day = d;
	}

	void puls(int years, int months,int days){
		day += days;
		month += months;
		year += years;
		while(month>12){
			month-=12;
			year++;
		}
		while(true){
			if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10){
				if(day>31){
					day -= 31;
					month++;
				}else{
					break;
				}
			}else if(month==4 || month==6 || month==9 || month==11){
				if(day>30){
					day -= 30;
					month++;
				}else{
					break;
				}
			}else if(month==2){
				if(year%4==0 && year%100!=0 || year%400==0){
					if(day>29){
						day -= 29;
						month++;
					}else{
						break;
					}
				}else{
					if(day>28){
						day-=28;
						month++;
					}else{
						break;
					}
				}
			}else if(month == 12){
				if(day>31){
					day-=31;
					month=1;
					year++;
				}else{
					break;
				}
			}
		}
	}

}
