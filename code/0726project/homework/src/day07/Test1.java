package day07;

public class Test1 {
	static class Employee{
		String number;//编号
		String name;//姓名
		int age;//年龄
		double wage;//工资
	}

	public static void main(String[] args) {
		Employee my1 = new Employee();
		my1.number = "1";
		my1.name = "张三";
		my1.age = 23;
		my1.wage = 10000.0;
		System.out.println("员工的编号: " + my1.number+", "+"姓名: " + my1.name+", "+"年龄: " + my1.age+", "+"薪资: " + my1.wage);

		Employee my2 = new Employee();
		my2.number = "2";
		my2.name = "李四";
		my2.age = 22;
		my2.wage = 11000.0;
		System.out.println("员工的编号: " + my2.number+", "+"姓名: " + my2.name+", "+"年龄: " + my2.age+", "+"薪资: " + my2.wage);




	}
}
