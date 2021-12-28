package day10.test8;/*package day10.test8;
//考核知识点：继承，super，static
//如下代码是否可以编译通过，如果能，结果是什么，如果不能，为什么？
public class Person {
	public Person(){
		System.out.println("this is a Person.");
	}
}
public class Teacher extends Person{
	private String name = "tom";
	public Teacher(){
		System.out.println("this is a teacher.");
//		super();//错误，super()必须在构造器首行
		super();
	}
	public static void main(String[] args){
		Teacher tea = new Teacher();
		System.out.println(this.name);
//		System.out.println(this.name);//错误，static方法中不能使用this
	}
}*/
