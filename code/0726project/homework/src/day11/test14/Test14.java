package day11.test14;
/*
1、私有的属性是否可以被继承到子类中？
（1）如果从可以访问性的角度来说：不能，因为在子类中不能直接访问父类的私有的属性，但是可以通过get/set操作
（2）如果从类的概念来说，
类是一类具有相同特性（属性、方法等）的事物的抽象描述，
那么子类是从父类派生出来的，那么子类是有父类的这个特征的，即有这个属性的

2、每一个对象的非静态属性是独立的，其中一个对象修改和另一个对象是无关的

3、当子类有与父类的属性同名时，那么通过子类对象调用get/set方法操作的是父类继承还是子类自己的属性呢？
要看子类是否重写：
如果没有重写，操作的都是父类的，不管是直接getInfo()还是this.getInfo()，还是super.getInfo()
如果重写了，如果通过子类对象调用，操作的是子类的，例如：getInfo()还是this.getInfo()，
		如果通过super.调用的，操作的是父类的。
 */
public class Test14 {
	public static void main(String[] args) {
		Father f = new Father();
		Son s = new Son();
		System.out.println(f.getInfo());//atguigu
		System.out.println(s.getInfo());//尚硅谷
		s.test();
		System.out.println("-----------------");
		s.setInfo("大硅谷");
		System.out.println(f.getInfo());//at
		System.out.println(s.getInfo());//大硅谷
		s.test();
	}
}
class Father{
	private String info = "atguigu";
	public void setInfo(String info){
		this.info = info;
	}
	public String getInfo(){
		return info;
	}
}
class Son extends Father{
	private String info = "尚硅谷";
	public void setInfo(String info){
		this.info = info;
	}
	public String getInfo(){
		return info;
	}
	public void test(){
		System.out.println(this.getInfo());//尚硅谷   大硅谷
		System.out.println(super.getInfo());//atguigu   atguigu
	}
}