package day14API.src.com.atguigu.string.s2;

import org.junit.Test;

import java.util.Arrays;

public class StringTest1 {

    @Test
    public void test05() {


        char[] cs = {'A', 'B', 'C'};

        char[] chars = Arrays.copyOf(cs, cs.length);




    }

    @Test
    public void test04() {


        char[] cs = {'A','B','C'};

        String s = new String(cs);




    }


    @Test
    public void test03() {
        /*
        intern():
                1.查看常量池内是否有此结果 有直接返回常量池的地址
                2.如果没有将此内容放到常量池 再返回常量池结果
         */
        String s1 = "Hello";
       String s2 = "World";

        String s6 = "Hello" +"World";
        String s3 = s1 + s2;

        String s4 = s1 + "World";
        String s5 = "Hello" +s2;//HelloWorld

        String s7 = "HelloWorld";

        System.out.println("s6==s7 = " + (s6 == s7));//true
        System.out.println("s3==s7 = " +( s3 == s7));//false
        System.out.println("s4==s7 = " +( s4 == s7));//false
        System.out.println("s5==s7 = " +( s5.intern() == s7));//false






    }



    @Test
    public void test02() {
        /*
        如果是 常量+常量 结果是字符串常量
        如果是 变量+变量 结果是创建一个StringBuilder对象 对字符串内容进行拼接
        如果是 变量+常量 结果是创建一个StringBuilder对象 对字符串内容进行拼接

         */
      final   String s1 = "Hello";
      final   String s2 = "World";

        String s6 = "Hello" +"World";
        String s3 = s1 + s2;

        String s4 = s1 + "World";
        String s5 = "Hello" +s2;

        String s7 = "HelloWorld";

        System.out.println("s6==s7 = " + (s6 == s7));//true
        System.out.println("s3==s7 = " +( s3 == s7));//false
        System.out.println("s4==s7 = " +( s4 == s7));//false
        System.out.println("s5==s7 = " +( s5 == s7));//false


    }




    @Test
    public void test01() {
        /*
        如果是 常量+常量 结果是字符串常量
        如果是 变量+变量 结果是创建一个StringBuilder对象 对字符串内容进行拼接
        如果是 变量+常量 结果是创建一个StringBuilder对象 对字符串内容进行拼接

         */
        String s1 = "Hello";
        String s2 = "World";

        String s6 = "Hello" +"World";
        String s3 = s1 + s2;

        String s4 = s1 + "World";
        String s5 = "Hello" +s2;

        String s7 = "HelloWorld";

        System.out.println("s6==s7 = " + (s6 == s7));//true
        System.out.println("s3==s7 = " +( s3 == s7));//false
        System.out.println("s4==s7 = " +( s4 == s7));//false
        System.out.println("s5==s7 = " +( s5 == s7));//false


    }
}
