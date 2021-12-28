package day11.test12;
/*
1、私有的属性是否可以被继承到子类中？
（1）如果从可以访问性的角度来说：不能，因为在子类中不能直接访问父类的私有的属性，但是可以通过get/set操作
（2）如果从类的概念来说，
类是一类具有相同特性（属性、方法等）的事物的抽象描述，
那么子类是从父类派生出来的，那么子类是有父类的这个特征的，即有这个属性的

2、每一个对象的非静态属性是独立的，其中一个对象修改和另一个对象是无关的
 */
public class Test12 {
	public static void main(String[] args) {
		Father f = new Father();
		Son s = new Son();
		System.out.println(f.getInfo());
		System.out.println(s.getInfo());
		s.setInfo("尚硅谷");
		System.out.println(f.getInfo());
		System.out.println(s.getInfo());
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
}