package day10.test11;

public class Man extends Person{
	@Override
	public void eat() {
		System.out.println("细嚼慢咽吃饭");
	}

	@Override
	public void toilet() {
		System.out.println("坐着上洗手间");
	}

	public void smoke(){
		System.out.println("抽烟");
	}
}
