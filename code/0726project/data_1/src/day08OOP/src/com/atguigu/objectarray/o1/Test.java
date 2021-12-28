package day08OOP.src.com.atguigu.objectarray.o1;

public class Test {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        c1.r=10;
       // c1.getInfo();

        Circle c2 = new Circle();
        c2.r=30;

       // int[] arr = {10,20,30};

        Circle[] cArr = new Circle[3];
        cArr[0]=c1;
        cArr[1]=c2;
        cArr[2]=new Circle();
        cArr[2].r=60;

        //数组第三个位置是 null null调用任何资源都会  NullPointerException
        for (Circle circle : cArr) {
            circle.getInfo();
        }
    }
}
