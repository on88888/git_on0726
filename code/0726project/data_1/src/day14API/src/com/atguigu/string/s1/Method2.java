package day14API.src.com.atguigu.string.s1;

import org.junit.Test;

import java.util.Arrays;

public class Method2 {
    @Test
    public void test06() {

        String s = "A1B2C3D4E";

        String[] split = s.split("\\d");
        System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
    }

    @Test
    public void test05() {

        String s = "A1B1C1D1E";

        String[] split = s.split("1");

        System.out.println("split.length = " + split.length);

        System.out.println("Arrays.toString(split) = " + Arrays.toString(split));


    }



    @Test
    public void test04() {

        String s = "ABCDAB";
        //替换指定的字符串
        String replace = s.replace("AB", "你好");

        System.out.println("replace = " + replace);


    }

    @Test
    public void test03() {

        String s = "ABCDA";
        //替换字符串内的指定字符
        String replace = s.replace('A', 'Z');
        System.out.println("replace = " + replace);


    }



    @Test
    public void test02() {

        String s = "ABCD";
//判断是否以指定字符串结束
        boolean cd = s.endsWith("CD1");

        System.out.println("cd = " + cd);

    }

    @Test
    public void test01() {

        String s = "ABCD";
        //判断是否以指定字符串开始
        boolean a = s.startsWith("A1");

        System.out.println("a = " + a);


    }
}
