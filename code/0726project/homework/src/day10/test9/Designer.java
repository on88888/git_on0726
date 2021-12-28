package day10.test9;

public class Designer extends Programmer{
	private double bonus;//奖金

	public Designer() {
	}

	public Designer(int id, String name, int age, double pay, double bonus) {
		super(id, name, age, pay);
		this.bonus = bonus;
	}

	public Designer(int id, String name, int age, double pay, String language, double bonus) {
		super(id, name, age, pay, language);
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String getInfo() {
		return super.getInfo()+ "\t" + bonus;
	}
}
