package day10.test6;
/*
 * 属性没有多态性，只看编译时类型
 */
public class Test6 {
		public static void main(String[] args) {
			Base b = new Sub();
			System.out.println(b.x);
		}
	}
	class Base{
		int x = 1;
	}
	class Sub extends Base{
		int x = 2;
	}
