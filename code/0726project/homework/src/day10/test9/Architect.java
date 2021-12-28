package day10.test9;

public class Architect extends Designer{
	private double guNums;

	public Architect() {
	}

	public Architect(int id, String name, int age, double pay, double bonus, double guNums) {
		super(id, name, age, pay, bonus);
		this.guNums = guNums;
	}

	public Architect(int id, String name, int age, double pay, String language, double bonus, double guNums) {
		super(id, name, age, pay, language, bonus);
		this.guNums = guNums;
	}

	public double getGuNums() {
		return guNums;
	}

	public void setGuNums(double guNums) {
		this.guNums = guNums;
	}

	@Override
	public String getInfo() {
		return super.getInfo() + "\t" + guNums;
	}

}
