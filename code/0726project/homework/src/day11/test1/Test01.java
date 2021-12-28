package day11.test1;
/*
 * 创建对象是通过执行实例初始化方法来完成的。
 * 如果new后面跟无参构造，就说明调用无参的实例初始化方法<init>()，
 * 如果new后面跟有参构造，就说明调用有参的实例初始化方法<init>(形参列表)。
 * 编译器编译后类中没有构造器，而是编译为一个个的实例初始化方法。
 * 实例初始化由：
 * （1）非静态成员变量的显式赋值代码
 * （2）非静态代码块代码
 * （3）构造器代码
 * 其中（1）（2）按编写顺序，（3）在最后
 * 在子类实例初始化首行会有super()或super(实参列表)表示调用父类的实例初始化方法，
 * 如果没写super()或super(实参列表)，那么默认就是super()，因此：
 * （1）先执行父类实例初始化
 * <init>(){
 * 		System.out.println("I'm A Class");
 * 		System.out.println("HelloA");
 * }
 * （2）再执行子类实例初始化
 * <init>(){
 * 		System.out.println("I'm B Class");
 * 		System.out.println("HelloB");
 * }
 */
class HelloA{
	public HelloA(){
		System.out.println("HelloA");
	}
	{
		System.out.println("I'm A Class");
	}
}

class HelloB extends HelloA{
	public HelloB(){
		System.out.println("HelloB");
	}
	{
		System.out.println("I'm B Class");
	}
}
public class Test01{
	public static void main(String[] args) {
		new HelloB();
	}
}
