package day11.test16;

public class Architect extends Designer {
	private int stock;

	public Architect() {
		super();
	}

	public Architect(int id, String name, int age, double salary, double bonus, int stock) {
		super(id, name, age, salary, bonus);
		this.stock = stock;
	}

	public Architect(int id, String name, int age, double salary, String language, double bonus, int stock) {
		super(id, name, age, salary, language, bonus);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String getInfo() {
		return super.getInfo() + "\t" + stock;
	}

}
