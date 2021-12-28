package day10.test4;
/*
 * 1、分析方法列表和继承关系
 * A类：
 * 	public String show(D obj)
 * 	public String show(A obj)
 * B类：
 * 	public String show(D obj)继承的
 * 	public String show(A obj)重写
 * 	public String show(B obj)自定义的
 * C->B->A
 * D->B->A
 *
 * 2、方法重载：找最合适的形参类型
 * 3、方法重写：如果子类重写，就执行重写的
 * 4、分析执行结果
 * a1.show(b)：a1没有多态引用，直接找A类的方法，b是B类对象，只能选择public String show(A obj)   A and A
 * a2.show(d)：a2多态引用，执行子类的方法，d是D类对象，选最合适的public String show(D obj)   A and D
 * b.show(c)：b没有多态引用，直接找B类的方法，c是C类的对象，选择最合适的public String show(B obj) B and B
 * b.show(d)：b没有多态引用，直接找B类的方法，d是D类对象，选最合适的public String show(D obj)   A and D
 */
public class Test4 {

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
	class A{
		public String show(D obj){
			return ("A and D");
		}
		public String show(A obj){
			return "A and A";
		}
	}
	class B extends A{
		public String show(B obj){
			return "B and B";
		}
		public String show(A obj){
			return "B and A";
		}
	}
	class C extends B{

	}
	class D extends B{

	}

