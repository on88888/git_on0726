package day08.Test06;

public class Test6 {
	public static void main(String[] args) {
		Rectangle cc = new Rectangle();
		cc.setL(6.5);
		cc.setW(3.5);
		System.out.println("cc.getArea() = " + cc.getArea());
		System.out.println("cc.getPerimeter() = " + cc.getPerimeter());
		cc.setL(cc.getL()*2);
		cc.setW(cc.getW()*2);
		System.out.println("cc.getInfo() = " + cc.getInfo());
	}
}
