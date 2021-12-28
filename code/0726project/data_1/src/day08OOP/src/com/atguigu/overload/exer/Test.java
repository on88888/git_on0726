package day08OOP.src.com.atguigu.overload.exer;

public class Test {
    public static void main(String[] args) {

        int[] arr = {30,10,20,70,60};

        String info = ArraysTools.getInfo(arr);
        System.out.println("排序前 = " + info);
        //排序

        ArraysTools.sort(arr);
        //排序后
        String info1 = ArraysTools.getInfo(arr);
        System.out.println("排序后 = " + info1);



    }
}
