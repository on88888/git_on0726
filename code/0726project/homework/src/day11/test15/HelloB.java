package day11.test15;
/*
 * 1、main是Java程序的入口，那么main所在的类需要先完成类初始化，才能执行main方法。
 * 即先完成HelloB的类初始化，才能执行main中的new Hello()
 * 2、但是在类初始化时，如果发现父类还没有初始化，会先初始化父类，即先完成HelloA的类初始化
 * 3、类初始化方法由：
 * （1）静态变量的显式赋值代码
 * （2）静态代码块代码
 * 4、 创建对象是通过执行实例初始化方法来完成的。
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
 *
 * 因此：
 * 1、先执行HelloA的类初始化
 * <clinit>(){
 * 		System.out.println("static A");
 * }
 * 2、在完成Hello的类初始化
 * <clinit>(){
 * 		System.out.println("static B");
 * }
 * 3、再执行父类HelloA的实例初始化方法
 * <init>(){
 * 		System.out.println("I'm A Class");
 * 		System.out.println("HelloA");
 * }
 * 4、最后执行子类HelloB的是实例初始化方法
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
	static{
		System.out.println("static A");
	}
}
public class HelloB extends HelloA{
	public HelloB(){
		System.out.println("HelloB");
	}
	{
		System.out.println("I'm B Class");
	}
	static{
		System.out.println("static B");
	}
	public static void main(String[] args) {
		new HelloB();
	}
}
