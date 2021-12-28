package day10.test2;

/*
 * 1、Father f = new Son();
 * 实例初始化的过程：
 * （1）父类的实例初始化
 * <init>(){
 * 		x = 10;//父类的x
 * 		this.print();//子类的print，因为this代表的是正在创建的子类对象，而子类重写了print，所以是子类的print'
 * 				System.out.println("Son.x = " + x);//子类的x，此时还没有赋值，那么是默认值x=0
		x = 20;//父类的x
 * }
 * （2）子类的实例初始化
 * <init>(){
 * 		x = 30;//子类的x
 * 		this.print();//子类的print
 * 			System.out.println("Son.x = " + x);//子类的x，此时已经赋值x=30
		x = 40;//子类的x
 * }
 *
 * 2、执行System.out.println(f.x);
 * 属性没有多态性，只看编译时类型，那么此时f.x表示父类的x
 */
public class Test2 {
	public static void main(String[] args) {
		Father f = new Son();
		System.out.println(f.x);//20
	}
}
class Father{
	int x = 10;
	public Father(){
		this.print();
		x = 20;
	}
	public void print(){
		System.out.println("Father.x = " + x);
	}
}
class Son extends Father{
	int x = 30;
	public Son(){
		this.print();
		x = 40;
	}
	public void print(){
		System.out.println("Son.x = " + x);
	}
}
