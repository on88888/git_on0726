package day10.test11;

public class Woman extends Person{
	@Override
	public void eat() {
		System.out.println("狼吞虎咽吃饭");
	}

	@Override
	public void toilet() {
		System.out.println("站着上洗手间");
	}

	public void makeup(){
		System.out.println("化妆");
	}
}
