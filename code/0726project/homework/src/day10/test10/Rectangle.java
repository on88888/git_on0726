package day10.test10;

public class Rectangle extends Graphic{
	double l;
	double w;

	public Rectangle(double l, double w) {
		this.l = l;
		this.w = w;
	}

	public Rectangle(String name, double l, double w) {
		super(name);
		this.l = l;
		this.w = w;
	}

	@Override
	public double getArea() {
		return l*w;
	}

	@Override
	public double getPerimeter() {
		return (l+w)*2;
	}

	public double getL() {
		return l;
	}

	public void setL(double l) {
		this.l = l;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	@Override
	public String getInfo() {
		return "长：" + l + "，宽：" + w + "," + super.getInfo();
	}
}
