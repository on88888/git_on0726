package day10.test7;/*package day10.test7;
//考核知识点：权限修饰符
//如下代码是否可以编译通过，如果能，结果是什么，如果不能，为什么？

public class Father {
		private String name = "atguigu";
		int age = 0;
}
	public class Child extends Father{
		public String grade;

		public static void main(String[] args){
			Father f = new Child();
			System.out.println(f.name);
	//		System.out.println(f.name);//编译错误，因为name私有化
		}
}*/

