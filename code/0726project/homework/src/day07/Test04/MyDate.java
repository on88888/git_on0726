package day07.Test04;

public class MyDate {
	int year,month,day;

	boolean isLeapYear(){
		return ((year&400)==0||year%4==0&&year%100!=0);
	}//只会返回  true 或者 false

	void set(int y,int m, int d){
		year = y;
		month = m;
		day = d;
	}

	void puls(int years,int months,int days){
		year += years;
		month += months;
		day += days;
		if(month>12){
			year +=1;
			month -=12;
		}
		switch(month){
			case 12:
				if(day>31){
					month+=1;
					day -=31;
				}
			case 11:
				if(day>30){
					month+=1;
					day-=30;
				}
			case 10:
				if(day>31){
					month+=1;
					day-=31;
				}
			case 9:
				if(day>30){
					month+=1;
					day -=30;
				}
			case 8:
				if(day>31){
					month+=1;
					day-=31;
				}
			case 7:
				if(day>31){
					month+=1;
					day-=31;
				}
			case 6:
				if(day>30){
					month+=1;
					day -=30;
				}
			case 5:
				if(day>31){
					month+=1;
					day-=31;
				}
			case 4:
				if(day>30){
					month+=1;
					day-=30;
				}
			case 3:
				if(day>31){
					month+=1;
					day -=31;
				}
			case 2:
				if(year%400==0||year%4==0&&year%100!=0){
					if(day>29){
						month+=1;
						day-=29;
					}
				}else if(day>28){
					month+=1;
					day-=28;
			}
			case 1:
				if(day>31){
					month+=1;
					day-=31;
				}

		}
	}
}
