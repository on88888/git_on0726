package day09OOP.src.com.atguigu.inheritance.exer;

public class Rectangle extends Graphic{
    int length;
    int width;

    public Rectangle(String name,int length,int width){
        super(name);
        this.length=length;
        this.width=width;
    }

    @Override
    public double getArea() {
        return length*width;
    }

    @Override
    public double getPerimeter() {
        return (length+width)*2;
    }
}
