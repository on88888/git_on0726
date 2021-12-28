package day11.test16;

public class Programmer extends Employee{
	private String language = "java";

	public Programmer() {
		super();
	}

	public Programmer(int id, String name, int age, double salary) {
		super(id, name, age, salary);
	}

	public Programmer(int id, String name, int age, double salary, String language) {
		super(id, name, age, salary);
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
