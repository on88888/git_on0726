package day10.test10;

public class Graphic {
	String name;

	public Graphic() {
	}

	public Graphic(String name) {
		this.name = name;
	}

	public double getArea(){
		return 0;
	}

	public double getPerimeter(){
		return 0;
	}

	public String getInfo(){
		return "面积："+getArea()+", 周长: "+getPerimeter();
	}
}