package day11.test5;
/*
 * 1、先算new B()
 * 执行B类的实例初始化方法：
 * <init>(){
 * 		System.out.println("B");
 * }
 * 2、再算new A(B对象)
 * 执行A类的实例初始化方法：
 * <init>(B b){
 * 		this();
 * 			即调用本类的无参构造，或者说无参实参初始化方法
 * 			<init>(){
 * 				System.out.println("A");
 * 			}
 *		System.out.println("AB");
 * }
 */
public class Test05 {
	public static void main(String[] args) {
		new A(new B());
	}
}
class A {
	public A() {
		System.out.println("A");
	}
	public A(B b) {
		this();
		System.out.println("AB");
	}
}
class B {
	public B() {
		System.out.println("B");
	}
}
