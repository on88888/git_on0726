package day08OOP.src.com.atguigu.overload.exer1;

public class GraphicTools {

    //根据底边和高求面积
    public static void getArea(int d,int h){
        System.out.println(d*h/2);
    }
    //根据三边求面积
    public void getArea(int a,int b,int c){

        int p = (a+b+c)/2;

        double tempArea = p*(p-a)*(p-b)*(p-c);

        double area = Math.sqrt(tempArea);

        System.out.println("area = " + area);


    }


}
