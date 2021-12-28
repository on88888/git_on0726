package day08OOP.src.com.atguigu.argments;

public class Test2 {
    public static void main(String[] args) {
        sum4(10,20,30,40,50);

        sum4(10,20);
    }

    private static void sum4(int...i) {
        int sum = 0;

        for (int i1 : i) {
            sum+=i1;
        }
        System.out.println(sum);

    }
}
