package day11.test7;
/*
 * （1）Father类的类初始化
 * ①类变量显式赋值：这里没有
 * ②静态代码块
 * 		System.out.println("（1）父类的静态代码块");
 * （2）Son类的类初始化
 * ①类变量显式赋值：这里没有
 * ②静态代码块
 * 		System.out.println("（4）子类的静态代码块");
 *
 * （3）执行Father类的是实参初始化方法<init>()
 * ①非静态成员变量的显式赋值：这里没有
 * ②非静态代码块：
 * 		System.out.println("（2）父类的非静态代码块");
 * ③父类的无参构造
 * 		System.out.println("（3）父类的无参构造");
 *
 * （4）执行Son类的实例初始化方法<init>()
 * ①非静态成员变量的显式赋值：这里没有
 * ②非静态代码块：
 * 		System.out.println("（5）子类的非静态代码块");
 * ③子类的无参构造
 * 		System.out.println("（6）子类的无参构造");
 */
public class Test07 {
	public static void main(String[] args) {
		Son son = new Son();
	}
}
class Father{
	static{
		System.out.println("（1）父类的静态代码块");
	}
	{
		System.out.println("（2）父类的非静态代码块");
	}
	Father(){
		System.out.println("（3）父类的无参构造");
	}
}
class Son extends Father{
	static{
		System.out.println("（4）子类的静态代码块");
	}
	{
		System.out.println("（5）子类的非静态代码块");
	}
	Son(){
		System.out.println("（6）子类的无参构造");
	}
}
