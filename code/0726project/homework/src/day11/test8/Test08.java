package day11.test8;
/*
 * （1）Fu类的类初始化
 * ①类变量显式赋值：
 * 		i = getNum("（1）i");
 * 		public static int getNum(String str){
			print(str);
				print方法代码如下：
				public static void print(String str){
					System.out.println(str + "->" + i);			(1)i -> 0（默认值）
				}
			return ++i;											i=1
		}
 * ②静态代码块
 * 	static{
		print("（3）父类静态代码块");
			print方法代码如下：
			public static void print(String str){
				System.out.println(str + "->" + i);			  （3）父类静态代码块 -> 1
			}
	}
 * （2）Zi类的类初始化
 * ①类变量显式赋值：
 * 	  k = getNum("（6）k");
	 *
		public static int getNum(String str){
			print(str);
				print方法代码如下：
				public static void print(String str){
					System.out.println(str + "->" + k);		（6）k -> 0（默认值）
				}
			return ++k;										k=1
		}
 * ②静态代码块
 * 	static{
		print("（8）子类静态代码块");
			print方法代码如下：
			public static void print(String str){
				System.out.println(str + "->" + k);		（8）子类静态代码块 -> 1
			}
	}
 *
 * （3）执行Fu类的是实参初始化方法<init>()
 * ①非静态成员变量的显式赋值：
 * 		j = getNum("（2）j");
	 *
		public static int getNum(String str){
			print(str);
				print方法代码如下：
				public static void print(String str){
					System.out.println(str + "->" + i);  （2）j -> 1
				}
			return ++i;									i=2
		}
 * ②非静态代码块：
 * 	{
		print("（4）父类非静态代码块，又称为构造代码块");
			print方法代码如下：
			public static void print(String str){
				System.out.println(str + "->" + i);  （4）父类非静态代码块，又称为构造代码块 -> 2
			}
	}
 * ③父类的无参构造
 *	Fu(){
		print("（5）父类构造器");
			print方法代码如下：
			public static void print(String str){
				System.out.println(str + "->" + i);  （5）父类构造器 -> 2
			}
	}
 *
 * （4）执行Zi类的实例初始化方法<init>()
 * ①非静态成员变量的显式赋值：
 * 	 h = getNum("（7）h");

	public static int getNum(String str){
		print(str);
			print方法代码如下：
			public static void print(String str){
				System.out.println(str + "->" + k);   （7）h ->1
			}
		return ++k;										k=2
	}
 *
 * ②非静态代码块：
 * 	{
		print("（9）子类非静态代码块，又称为构造代码块");
			print方法代码如下：
			public static void print(String str){
				System.out.println(str + "->" + k);   （9）子类非静态代码块，又称为构造代码块 ->2
			}
	}
 * ③子类的无参构造
 * 	Zi(){
		print("（10）子类构造器");
			print方法代码如下：
			public static void print(String str){
				System.out.println(str + "->" + k);   （10）子类构造器 ->2
			}
	}
 */
public class Test08 {
	public static void main(String[] args) {
		Zi zi = new Zi();
	}
}
class Fu{
	private static int i = getNum("（1）i");
	private int j = getNum("（2）j");
	static{
		print("（3）父类静态代码块");
	}
	{
		print("（4）父类非静态代码块，又称为构造代码块");
	}
	Fu(){
		print("（5）父类构造器");
	}
	public static void print(String str){
		System.out.println(str + "->" + i);
	}
	public static int getNum(String str){
		print(str);
		return ++i;
	}
}
class Zi extends Fu{
	private static int k = getNum("（6）k");
	private int h = getNum("（7）h");
	static{
		print("（8）子类静态代码块");
	}
	{
		print("（9）子类非静态代码块，又称为构造代码块");
	}
	Zi(){
		print("（10）子类构造器");
	}
	public static void print(String str){
		System.out.println(str + "->" + k);
	}
	public static int getNum(String str){
		print(str);
		return ++k;
	}
}