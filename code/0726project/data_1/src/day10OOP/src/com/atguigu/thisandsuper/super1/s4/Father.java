package day10OOP.src.com.atguigu.thisandsuper.super1.s4;


public  class Father{
	protected int num = 10;
	public int getNum(){
		return num;
	}
}
class Son extends Father{
	private int num = 20;
	public void test(){
		System.out.println(getNum());//10      20  10
		System.out.println(this.getNum());//20     10
		System.out.println(super.getNum());//10    10
	}
}
class Daughter extends Father{
	private int num = 20;
	public int getNum(){
		return num;
	}
	public void test(){
		System.out.println(this.getNum());     //20
		System.out.println(this.getNum());//20
		System.out.println(super.getNum());// 10
	}
}