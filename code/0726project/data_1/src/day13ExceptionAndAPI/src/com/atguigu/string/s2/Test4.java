package day13ExceptionAndAPI.src.com.atguigu.string.s2;

import org.junit.Test;

import java.util.Arrays;

public class Test4 {

    @Test
    public void test03(){

        String s = "DBCA";

        char[] chars = s.toCharArray();
        System.out.println("Arrays.toString(chars) = " + Arrays.toString(chars));


        Arrays.sort(chars);
        System.out.println("Arrays.toString(chars) = " + Arrays.toString(chars));


    }



    @Test
    public void test02(){

        String s = "锄禾日当午，汗滴禾下土";
        //i 代表下标
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            System.out.println("c = " + c);
        }

    }


    @Test
    public void test01(){

        String s = "锄禾日当午，汗滴禾下土";

        System.out.println("s.length() = " + s.length());


        System.out.println("s.charAt(0) = " + s.charAt(0));

        System.out.println("s.charAt(s.length()-1) = " + s.charAt(s.length() - 1));


    }
}
