package day11OOP.src.com.atguigu.poly.exer2;

public class TestOverload {
	public static void main(String[] args) {
		Father f = new Father();
		Father s = new Son();
		Father d = new Daughter();
        
     MyClass my = new MyClass();
		my.method(f);//
		my.method(s);//
		my.method(d);//
	}
}