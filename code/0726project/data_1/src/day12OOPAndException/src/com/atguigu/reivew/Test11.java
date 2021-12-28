package day12OOPAndException.src.com.atguigu.reivew;

public class Test11 {
	static int x, y, z;
	static {
		int x = 5;
		x--;
	}
	static {
		//成员变量x
		x--;
	}
	public static void main(String[] args) {
		System.out.println("x=" + x);
		z--;
		method();
		System.out.println("result:" + (z + y + ++z));
	}
	public static void method() {
		y =  z++ + ++z;//0
	}
}