package day14API.src.com.atguigu.string.s2;

public class StringTest3 {
    public static void main(String[] args) {

        String s = "你好";
        System.out.println("前 = " + s);
        changValue(s);
        System.out.println("后 = " + s);
    }

    private static void changValue(String s) {
        s = "世界";
        System.out.println("中 = " + s);
    }
}
