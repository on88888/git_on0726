package day13ExceptionAndAPI.src.com.atguigu.string.s2;

import org.junit.Test;

import java.util.Scanner;

public class Test2 {
    @Test
    public void test07() {
        String s = "      A     B      ";
        String s1 = "你好世界";
        System.out.println(s1 + s + s1);
        //去除字符串两端的空格
        String trim = s.trim();
        System.out.println(s1 + trim + s1);

    }

    @Test
    public void test06() {

        String s = "A";
        String s1 = "a";
        //忽略大小写的编码值比较
        int i = s.compareToIgnoreCase(s1);
        System.out.println("i = " + i);
    }


    @Test
    public void test05() {

        String s1 = "B";
        String s2 = "c";
        //按照编码值比较
        int i = s1.compareTo(s2);
        System.out.println("i = " + i);


    }

    @Test
    public void test04() {

        String s = "ABC";
        String s1 = "abc";

        boolean equals = s.equals(s1);
        System.out.println("equals = " + equals);
        //忽略大小写的比较
        boolean b = s.equalsIgnoreCase(s1);
        System.out.println("b = " + b);


    }


    @Test
    public void test03() {

        String s1 = "你好";
        String s2 = "你好";
        System.out.println(s1 == s2);//true

        String s3 = new String("你好");

        System.out.println(s3 == s1);
        //比较字符串内容
        boolean equals = s3.equals(s1);
        System.out.println("equals = " + equals);


    }


    @Test
    public void test02() {


        String s1 = "ABC你好世界def";
        //将字母变为大写
        String s = s1.toUpperCase();
        System.out.println("s = " + s);

        String s2 = s1.toLowerCase();
        System.out.println("s2 = " + s2);


        System.out.println("s1 = " + s1);

    }

    @Test
    public void test01() {
        Scanner input = new Scanner(System.in);
        String ss = input.next();
        System.out.println("ss = " + ss);
        String s1 = "hello";
        String s2 = "world";
        //字符串的拼接
        String concat = s1.concat(s2);

        System.out.println("concat = " + concat);

        System.out.println("s1 = " + s1);

    }

}
