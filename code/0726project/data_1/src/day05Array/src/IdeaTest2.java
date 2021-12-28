package day05Array.src;

public class IdeaTest2 {

    public static void main(String[] args) {

        String[] arr = {"张三","李四","王五"};


        for (int i = 0; i < arr.length; i++) {

            System.out.println("arr[i] = " + arr[i]);

        }
        System.out.println("------------------------------");
        for (String s : arr) {

            System.out.println("s = " + s);
        }

    }
}
