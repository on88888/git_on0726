package day09OOP.src.com.atguigu.inheritance.exer;

public class Circle extends Graphic {
    int r;
    public Circle(String name,int r){
        //调用父类有参构造器
        super(name);
        this.r=r;
    }
    @Override
    public double getArea() {
        return r*r*Math.PI;
    }
    @Override
    public double getPerimeter() {
        return 2*Math.PI*r;
    }
    @Override
    public String getInfo() {
        return super.getInfo()+"半径是："+r;
    }
}
