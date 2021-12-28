package day07.Test5;

/*
声明一个三角形类Triangle，包含属性：a,b,c，表示三条边，包含几个方法：
1、boolean  isRightTriangle()：判断是否是一个直角三角形
2、boolean isIsoscelesTriangle()：判断是否是一个等腰三角形
3、boolean isEquilateralTriangle()：判断是否是一个等边三角形
4、double getArea()：根据三条边，用海伦公式求面积
5、double getLength()：求周长
	并在测试类Test05的main方法中调用测试
 */
public class Test5 {
	public static void main(String[] args) {
		Triangle t = new Triangle();
		t.a = 3;
		t.b = 4;
		t.c = 5;

		System.out.println("是否是直接三角形：" + t.isRightTriangle());
		System.out.println("是否是等腰三角形：" + t.isIsoscelesTriangle());
		System.out.println("是否是等边三角形：" + t.isEquilateralTriangle());
		System.out.println("三角形的面积：" + t.getArea());
		System.out.println("三角形的周长：" + t.getLength());
	}
}
