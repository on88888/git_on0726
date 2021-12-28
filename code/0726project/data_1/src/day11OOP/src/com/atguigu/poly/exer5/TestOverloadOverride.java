package day11OOP.src.com.atguigu.poly.exer5;

public class TestOverloadOverride {
	public static void main(String[] args) {
		MyClass my = new MySub();

		Father f = new Father();
		Son s = new Son();
		Daughter d = new Daughter();
		my.method(f);//

		my.method(s);//

		my.method(d);//sub--
	}
}