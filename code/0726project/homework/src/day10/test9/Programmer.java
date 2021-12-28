package day10.test9;

public class Programmer extends Employee{
	private String language = "java";

	public Programmer() {
	}

	public Programmer(int id, String name, int age, double pay) {
		super(id, name, age, pay);
	}


	public Programmer(int id, String name, int age, double pay, String language) {
		super(id, name, age, pay);
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String getInfo() {
		return super.getInfo() + "\t" + language;
	}
}
