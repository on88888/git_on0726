package day11.test3;
/*
 * 1、Father f = new Father();
 * 执行父类的实例初始化方法
 * <init>(){
 * 		System.out.println("father create");
 * }
 *
 * 2、Child c = new Child();
 * （1）先执行父类的实例初始化方法
 * <init>(){
 * 		System.out.println("father create");
 * }
 * （2）再执行子类的实例初始化方法
 * <init>(){
 * 		System.out.println("child create");
 * }
 */
public class Test03 {
	public static void main(String[] args) {
		Father f = new Father();
		Child c = new Child();
	}
}
class Father {
	public Father(){
		System.out.println("father create");
	}
}
class Child extends Father{
	public Child(){
		System.out.println("child create");
	}
}
