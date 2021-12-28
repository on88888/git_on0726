package day10.test10;

public class Test {
	/*static String equalArea(String n1,String n2,double s1,double s2){
		return s1-s2==0?"面积相等":"面积不相等";
	}

	static String bigArea(String n1,String n2,double s1,double s2){
		return s1>s2?n1+"的面积更大":n2+"的面积更大";
	}*/
	public static boolean equal(Graphic g1, Graphic g2){
		return g1.getArea() == g2.getArea();
	}

	public static Graphic getMax(Graphic g1, Graphic g2){
		return g1.getArea() > g2.getArea() ? g1 : g2;
	}
	public static void main(String[] args) {
		Circle c1 = new Circle(2);
		Rectangle r1 = new Rectangle(2, 3);
		Triangle t1 = new Triangle(3, 4, 5);
		System.out.println("c1的面积：" + c1.getArea());
		System.out.println("r1的面积：" + r1.getArea());
		System.out.println("t1的面积：" + t1.getArea());

		System.out.println("c1和r1的面积是否相等：" + equal(c1, r1));
		System.out.println("c1和t1的面积是否相等：" + equal(c1, t1));
		System.out.println("r1和t1的面积是否相等：" + equal(r1, t1));
	}


}
