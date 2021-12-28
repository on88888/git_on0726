package day11.test9;
/*
 * 对于T来说，就完成类初始化
 *
 * 创建对象，调用类的实例初始化<init>()或<init>(String str)
 *
 * （1）静态变量的显式赋值
 * 		k = 0;
		t1 = new T("t1");
			<init>(String str)
				①j = print("j");
						print方法代码如下：
						public static int print(String str){
							System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);  1：j i=0 n=0
							++n;									n=1  k=1
							return ++i;								i=1
						}
				②	{
					print("构造块");
						print方法代码如下：
						public static int print(String str){
							System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);  2：构造块 i=1 n=1
							++n;									n=2  k=2
							return ++i;								i=2
						}
				}
				③public T(String str){
					System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);	  3:t1  i=2  n=2
					++n;											n=3  k=3
					++i;											i=3
				}
 * 		t2 = new T("t2");
			<init>(String str)
				①j = print("j");
						print方法代码如下：
						public static int print(String str){
							System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);  4：j i=3 n=3
							++n;									n=4  k=4
							return ++i;								i=4
						}
				②	{
					print("构造块");
						print方法代码如下：
						public static int print(String str){
							System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);  5：构造块 i=4 n=4
							++n;									n=5  k=5
							return ++i;								i=5
						}
				}
				③public T(String str){
					System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);	  6:t2  i=5  n=5
					++n;											n=6  k=6
					++i;											i=6
				}
	 i = print("i");
	 		print方法代码如下：
	 		public static int print(String str){
				System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);  7：i  i=6 n=6
				++n;									n=7  k=7
				return ++i;								i=7
			}
	n = 99;
 * （2）静态代码块
 * 	static{
		print("静态块");
			print方法代码如下：
			public static int print(String str){
				System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);  8：静态块   i=7 n=99
				++n;									n=100  k=8
				return ++i;								i=8
			}
	}
 */
public class T {
	public static int k = 0;
	public static T t1 = new T("t1");
	public static T t2 = new T("t2");
	public static int i = print("i");
	public static int n = 99;

	public int j = print("j");
	{
		print("构造块");
	}

	static{
		print("静态块");
	}
	public T(String str){
		System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);
		++n;
		++i;
	}
	public static int print(String str){
		System.out.println((++k) + ":" + str + "  i=" + i + "  n=" + n);
		++n;
		return ++i;
	}
	public static void main(String[] args) {

	}
}
