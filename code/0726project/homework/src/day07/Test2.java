package day07;

public class Test2 {
	static class MyData{
		int year,month,day;
	}

	public static void main(String[] args) {
		MyData data = new MyData();
		data.year = 1999;
		data.month = 8;
		data.day = 25;
		System.out.println("出生日期："+data.year+"年"+data.month+"月"+data.day+"日");
		MyData data1 = new MyData();
		data1.year = 2021;
		data1.month = 7;
		data1.day = 26;
		System.out.println("来尚硅谷的日期："+data1.year+"年"+data1.month+"月"+data1.day+"日");
		MyData data2 = new MyData();
		data2.year = 2022;
		data2.month = 6;
		data2.day = 31;
		System.out.println("毕业的日期："+data2.year+"年"+data2.month+"月"+data2.day+"日");
	}
}
