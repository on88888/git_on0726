package day11.test6;
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
 * 1、执行父类的实例初始化方法
 * <ini>(){
 * 		System.out.println("base");
 * 		method(100); //因为此时在创建子类的对象过程中，所以这个method(100)方法是
 * 						子类对象再调用，那么又因为子类重写了method(int)方法，
 * 						所以执行子类的method(int)
 * 					即System.out.println("sub : " + j);
 * }
 *
 * 2、执行子类的实例初始化方法
 * <init>(){
 * 		System.out.println("sub");
 * 		super.method(70);//因为这里用super.，那么一定是调用父类的method(int)
 * 					即System.out.println("base : " + i);
 * }
 */
public class Test06 {
	public static void main(String[] args) {
		Sub s = new Sub();
	}
}
class Base{
	Base(){
		method(100);//  this.method(100);调用当前对象的method，现在new子类对象，当前对象是子类，子类重写了method，这里执行子类的method
	}
	{
		System.out.println("base");
	}
	public void method(int i){
		System.out.println("base : " + i);
	}
}
class Sub extends Base{
	Sub(){
		super.method(70);
	}
	{
		System.out.println("sub");
	}
	public void method(int j){
		System.out.println("sub : " + j);
	}
}
