package day08.Test06;

public class Rectangle {
	private double l;
	private double w;

	public Rectangle() {
	}

	public Rectangle(double l, double w) {
		this.l = l;
		this.w = w;
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

	public double getArea(){
		return l*w;
	}

	public double getPerimeter(){
		return (l+w)*2;
	}

	public String getInfo(){
		return "长："+l+",宽："+w+",面积："+getArea()+",周长："+getPerimeter();
	}
}
