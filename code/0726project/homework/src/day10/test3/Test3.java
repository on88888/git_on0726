package day10.test3;
/*
 * 1、Base b1 = new Base();
 * 父类的实例初始化，和子类无关
 *
 * <init>(){
 * 		method(100);
 * 			System.out.println("base : " + i);  base:100
 * }
 *
 * 2、Base b2 = new Sub();
 * （1） 父类的实例初始化
 *
 * <init>(){
 * 		method(100);//执行了子类重写的method()
 * 			System.out.println("sub : " + j);  sub:100
 * }
 *
 * （2）子类的实例初始化
 * <init>(){
 * 		super.method(70);
 * 			System.out.println("base : " + i);	base:70
 * }
 */
public class Test3 {
	public static void main(String[] args) {
		Base b1 = new Base();
		Base b2 = new Sub();
//		base : 100
//		sub : 100
//		base : 70
	}
}

class Base {
	Base() {
		method(100);
	}

	public void method(int i) {
		System.out.println("base : " + i);
	}
}

class Sub extends Base {
	Sub() {
		super.method(70);
	}

	public void method(int j) {
		System.out.println("sub : " + j);
	}
}
