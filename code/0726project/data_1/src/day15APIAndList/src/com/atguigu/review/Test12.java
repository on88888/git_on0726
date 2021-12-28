package day15APIAndList.src.com.atguigu.review;
public class Test12 {
	public static void main(String[] args) {
		Father f = new Father();
		Son s = new Son();
		System.out.println(f.getInfo());//atguigu
		System.out.println(s.getInfo());//atguigu
		s.setInfo("尚硅谷");
		System.out.println(f.getInfo());//atguigu
		System.out.println(s.getInfo());//尚硅谷
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