package day15APIAndList.src.com.atguigu.wrapper;

import org.junit.Test;
/*
字符串与基本数据类型之间的转换

1.字符串--->基本数据类型
     使用对应的包装类型.parse基本数据类型
     Integer.parseInt(s);

注意：
    1.转为数字时 必须是 纯数字字符串 否则 NumberFormatException
    2.转为char时 需要借助字符串中的 charAt();
2.基本数据类型--->字符串
   2.1 + 拼接
   2.2  String.valueOf(m);
 */
public class W4Test {

    @Test
    public void test04(){
        String s = "ABC";
        char c = s.charAt(0);
    }
    @Test
    public void test03(){

        String s = "10";

        int i = Integer.parseInt(s);
        System.out.println("i = " + i);

        String s1 = "3.14";

        double d = Double.parseDouble(s1);
        System.out.println("d = " + d);


        String s2 = "100";
        long l = Long.parseLong(s2);

        System.out.println("l = " + l);


    }



    @Test
    public void test02(){

        int m = 10;

        String s = String.valueOf(m);
        double d = 3.14;
        String s1 = String.valueOf(d);
        boolean b = false;
        String s2 = String.valueOf(b);

    }



    @Test
    public void test01(){
        int m = 10;

        String s = ""+m;

    }
}
