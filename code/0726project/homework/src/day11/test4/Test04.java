package day11.test4;
/*
 * 当父类与子类有同名的属性时：
 * 通过子类对象调用getName()访问的是父类的name还是子类的name，
 * 那么要看子类是否重写，如果没有重写，就是父类的，重写了就是子类的。
 */
public class Test04 extends Father{
	private String name = "test";

	public static void main(String[] args) {
		Test04 test = new Test04();
		System.out.println(test.getName());
		//没有重写父类的方法，所以最后结果是返回父类
	}
}
class Father {
	private String name = "father";

	public String getName() {
		return name;
	}
}
