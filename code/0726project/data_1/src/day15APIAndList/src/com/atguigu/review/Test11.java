package day15APIAndList.src.com.atguigu.review;

public class Test11 {
	//成员变量
	static int x, y, z;//0

	static {
		//局部变量
		int x = 5;
		x--;//局部变量-1
		int m  =10;
	}
	static {
		x--;
		//成员变量 -1
	}
	public static void main(String[] args) {
		System.out.println("x=" + x);//-1
		z--;
		//z-- 执行完毕后 z=-1

		method();                     //1 + 0  + 2
		System.out.println("result:" + (z + y + ++z)); //3 4
	}
	public static void method() {
			//-1    1
		y =  z++ + ++z;
		//y = 0;
	}
}