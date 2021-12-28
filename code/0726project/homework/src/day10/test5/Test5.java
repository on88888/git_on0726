package day10.test5;

/*
 * 1、分析每个类的方法列表和继承关系
 * A类：
 * 	public String show(C obj)
 * 	public String show(A obj)
 * B类：
 * 	public String show(C obj)继承的
 * 	public String show(A obj)重写
 * 	public String show(B obj)自定义的
 * C->B->A
 * D->B->A
 *
 * 2、方法重载：找最合适的形参类型
 * 3、方法重写：如果子类重写，就执行重写的
 * 4、如果特殊的重载，那么多态时，编译时先从父类中查找最合适的形参类型，然后如果子类如果有重写，执行子类重写的，如果没有重写，执行父类的。
 * 5、分析执行结果
 * a1.show(b)：a1没有多态引用，直接找A类的方法，b是B类对象，只能选择public String show(A obj)   A and A
 * a2.show(d)：a2多态引用，执行子类的方法，d是D类对象，但是因为此时编译时按A类编译，所以在编译期间先确定是调用
 * 		public String show(A obj)，而后执行子类重写的public String show(A obj)       B and A
 * 		而不是直接选最合适的public String show(B obj)
 * b.show(c)：b没有多态引用，直接找B类的方法，c是C类的对象，选择最合适的public String show(C obj) A and C
 * b.show(d)：b没有多态引用，直接找B类的方法，d是D类对象，选最合适的public String show(B obj)   B and B
 */
public class Test5 {
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		C c = new C();
		D d = new D();
		System.out.println("(1)" + a1.show(b));
		System.out.println("(2)" + a2.show(d));
		System.out.println("(3)" + b.show(c));
		System.out.println("(4)" + b.show(d));
	}
}

class A {
	public String show(C obj) {
		return ("A and C");
	}

	public String show(A obj) {
		return "A and A";
	}
}

class B extends A {
	public String show(B obj) {
		return "B and B";
	}

	public String show(A obj) {
		return "B and A";
	}
}

class C extends B {

}

class D extends B {

}


