package day11.test2;
/*
 * 实例初始化的过程：
 * (1)父类的实例初始化
 * <init>(){
 * 		System.out.print("1");
 * }
 * (2)子类的实例初始化
 * <init>(String name){
 * 		System.out.print("3");
 * 		father = new People(name + " F");//创建了一个父类的对象
 * 			调用父类的<init>(String name){
 * 					System.out.print("2");
 * 			}
 * }
 *
 */
public class Test02 {
	public static void main(String[] args) {
		new Child("mike");
	}
}

class People {
	private String name;

	public People() {
		System.out.print("1");
	}

	public People(String name) {
		System.out.print("2");
		this.name = name;
	}
}

class Child extends People {
	People father;

	public Child(String name) {
		super();
		System.out.print("3");
		father = new People(name + " F");
	}

	public Child() {
		System.out.print("4");
	}
}

