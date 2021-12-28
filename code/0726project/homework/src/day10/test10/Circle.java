package day10.test10;

public class Circle extends Graphic{
	double r;

	public Circle(double r) {
		this.r = r;
	}

	public Circle(String name, double r) {
		super(name);
		this.r = r;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	@Override
	public double getArea() {
		return Math.PI*r*r;
	}

	@Override
	public double getPerimeter() {
		return Math.PI*r*2;
	}

	@Override
	public String getInfo() {
		return "半径：" + r + "," + super.getInfo();
	}
}
