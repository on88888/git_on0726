package day08.Test03;

public class Test3 {
	public static void main(String[] args) {
		MyDate yyy = new MyDate(2024,12,23);
		yyy.showDate();
		boolean flag = yyy.isLeapYear();
		System.out.println(yyy.getYear()+ (flag?"是闰年":"不是闰年"));
		/*if(flag){
			System.out.println(yyy.getYear()+"是闰年");
		}else{
			System.out.println(yyy.getYear()+"不是闰年");
		}*/
	}

}
