package day11OOP.src.com.atguigu.poly.exer4;

public class TestOverload {
	public static void main(String[] args) {
		//向上转型
		MyClass my = new MySub();

		Father f = new Father();
		Son s = new Son();
		Daughter d = new Daughter();

		my.method(f);//
		my.method(s);
		my.method(d);//
	}
}