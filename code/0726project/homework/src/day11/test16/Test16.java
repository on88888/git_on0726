package day11.test16;

public class Test16 {
	public static void main(String[] args) {
		Employee emp = new Employee(1, "张三", 23, 13000);
		Programmer pro = new Programmer(2, "李四", 23, 14000);
		Designer des = new Designer(3, "王五", 25, 15000, "scalar", 2000);
		Architect arc = new Architect(4, "赵六", 26, 16000, 3000, 100);

		System.out.println("编号\t姓名\t年龄\t薪资\t语言\t奖金\t股票");
		System.out.println(emp.getInfo());
		System.out.println(pro.getInfo());
		System.out.println(des.getInfo());
		System.out.println(arc.getInfo());
	}
}

