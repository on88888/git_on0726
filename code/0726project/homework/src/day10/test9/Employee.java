package day10.test9;

public class Employee {
	private int id;
	private String name;
	private int age;
	private double pay;

	public Employee() {
	}

	public Employee(int id, String name, int age, double pay) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.pay = pay;
	}

	public String getInfo(){
		return id + "\t" + name + "\t" + age + "\t" + pay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}
}
