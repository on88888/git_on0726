package day09OOP.src.com.atguigu.inheritance.exer;

public class Graphic {
    private String name;

    public Graphic(String name) {
        this.name = name;
    }
    public double getArea(){
        return 0.0;
    }
    public double getPerimeter(){
        return 0.0;
    }
    public String getInfo(){
        return "名字："+name+",面积："+getArea()+",周长："+getPerimeter();
    }

}
