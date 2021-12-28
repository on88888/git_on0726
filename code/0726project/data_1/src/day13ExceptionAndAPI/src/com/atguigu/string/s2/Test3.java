package day13ExceptionAndAPI.src.com.atguigu.string.s2;

import org.junit.Test;

/*
字符串查找
 */
public class Test3 {

    @Test
    public void test01() {
        System.out.println();
        System.out.println();
    }

    @Test
    public void test02() {

        String s = "ABCD";
        // 判断指定内容是否在字符串内出现过
        boolean cd = s.contains("CD1");

        System.out.println("cd = " + cd);

    }

    @Test
    public void test03() {
        //字符串元素 存在下标 从0开始
        String s = "ABCDA";

        //查找指定元素第一次出现的下标 没有返回-1
        int b = s.indexOf("1A");
        System.out.println("b = " + b);


    }


    @Test
    public void test04() {
        String s = "ABCDA";
        //查找指定元素最后一次出现的下标 没有返回-1
        int a = s.lastIndexOf("A1");
        System.out.println("a = " + a);


        String s1 = "你好世界你";


        int index = s1.lastIndexOf("你");

        System.out.println("index = " + index);
    }
}
