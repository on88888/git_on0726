package day11OOP.src.com.atguigu.poly.exer3;

public class TestOverload {
	public static void main(String[] args) {
		MyClass my = new MyClass();

		Father f = new Father();
		Son s = new Son();
		Daughter d = new Daughter();

		my.method(f);//
		my.method(s);//
		//Father 是Daughter的父亲 父类类型做形参 可以接受任意子类对象
		my.method(d);//
	}
}