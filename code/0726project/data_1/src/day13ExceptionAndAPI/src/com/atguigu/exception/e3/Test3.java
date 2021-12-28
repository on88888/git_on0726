package day13ExceptionAndAPI.src.com.atguigu.exception.e3;

public class Test3 {
    public static void main(String[] args) {
        int num = getNum();
        System.out.println("num = " + num);


    }

    private static int getNum() {
        int num = 10;
        try {
            int[] arr = {10, 20};
            System.out.println("arr[1] =  " + arr[1]);
            //temp
            return num;
        } catch (Exception e) {
            num = 30;
            return num;
        } finally {
            num = 40;
            // return num;
        }

    }
}
