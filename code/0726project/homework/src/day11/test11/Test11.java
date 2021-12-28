package day11.test11;
/*
 * （1）类的初始化
 * <clinit>(){
 * 		int x = 5;//局部变量
		x--;//局部变量		x=4
 * 		Test07.x--;//静态变量      x = -1
 * }
 * （2）执行main方法
 * System.out.println("x=" + x);//静态变量   -1
 * z--;//静态变量   z=-1
 * method();
 * 		y = z++ + ++z;//静态变量
 * 			①先加载z的值“-1”②z自增，z=0③z自增 z =1④加载z的值“1” ⑤求和  “-1” + “1” = 0⑥把0赋值给y   y=0
 * System.out.println("result:" + (z + y + ++z));
 * 			①加载z的值“1”  ②加载y的值"0" ③z自增  z=2 ④加载z的值“2”  ⑤求和  “1” + “0” + “2”
 *
 */
public class Test11 {
	static int x, y, z;//类变量，静态变量，成员变量   默认值0
	static {
		int x = 5;//局部变量
		x--;//局部变量
	}
	static {
		x--;//静态变量
	}
	public static void main(String[] args) {
		System.out.println("x=" + x);//静态变量
		z--;//静态变量
		method();
		System.out.println("result:" + (z + y + ++z));//静态变量
	}
	public static void method() {
		y = z++ + ++z;//静态变量
	}
}
