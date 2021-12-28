package day08OOP.src.com.atguigu.overload.exer1;

public class Test {

    public static void main(String[] args) {
        //3 4 5
        GraphicTools.getArea(3, 4);

        System.out.println("-------------------");
        GraphicTools g = new GraphicTools();

        g.getArea(3, 4, 5);

    }

}
