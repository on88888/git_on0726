package day07;

public class Test3 {
	static class Citizen{
		String name;
		MyDate birth;
		String number;
	}
	static class MyDate{
		int year,month,day;
	}

	public static void main(String[] args) {
//		MyDate birth = new MyDate();
//		birth.year=1968;
//		birth.month = 9;
//		birth.day = 26;

		Citizen bb = new Citizen();
		bb.name = "噢噢噢";
		bb.number = "202184";
//		bb.birth = birth;

		bb.birth = new MyDate();
		bb.birth.year = 1968;
		bb.birth.month = 8;
		bb.birth.day= 26;


		System.out.println("姓名："+bb.name+"\n"+"生日："+bb.birth.year+"年"+bb.birth.month+"月"+bb.birth.day+"日"+"\n"+"身份证号："+bb.number);

	}
}
