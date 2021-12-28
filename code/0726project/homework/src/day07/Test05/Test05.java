package day07.Test05;

public class Test05 {
	public static void main(String[] args) {
		Triangle t = new Triangle();
		t.a = 6;
		t.b = 6;
		t.c = 6;

		System.out.println("是否是直角三角形：" + t.isRightTriangle());
		System.out.println("是否是等腰三角形：" + t.isIsoscelesTriangle());
		System.out.println("是否是等边三角形：" + t.isEquilateralTriangle());
		System.out.println("三角形的面积：" + t.getArea());
		System.out.println("三角形的周长：" + t.getLength());
	}

}
