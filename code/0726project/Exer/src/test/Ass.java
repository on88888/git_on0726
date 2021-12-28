package test;

import java.util.Scanner;

/**
 * @Author 0726
 * @ClassName Ass
 * @createTime 2021年11月29日 21:10
 */
public class Ass {
/*    public String solve (String str) {
        // write code here


    }*/

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String next = input.next();
        String s = reverse1(next);
        System.out.println(s);

    }
    public static String reverse1(String str) {
        return new StringBuilder(str).reverse().toString();
    }


}

