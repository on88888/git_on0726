package day08OOP.src.com.atguigu.method.exer3;

public class Rectangle {

    int length;
    int width;
    //求面积
    public int getArea(){
        return length*width;
    }

    //求周长
    public int getPerimeter(){

        return (length+width)*2;
    }

    public String getInfo(){

        int area = getArea();
        return "长："+length+"，宽："+width+",面积是:"+area+",周长是："+getPerimeter();
    }

}
