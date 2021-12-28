package day10.test10;

public class Triangle extends Graphic{
	double a,b,c;

	public Triangle(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Triangle(String name, double a, double b, double c) {
		super(name);
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	@Override
	public double getArea() {
		double p = (a + b + c)/2;
		return Math.sqrt(p * (p-a) * (p-b) * (p-c));
	}
	@Override
	public double getPerimeter() {
		return a+b+c;
	}
	@Override
	public String getInfo() {
		return "边长为：" + a + "," + b +"," + c+"," + super.getInfo();
	}
}
