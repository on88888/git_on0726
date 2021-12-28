package day14API.src.com.atguigu.string.s1;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Method1 {
    /*
    ABCDE
    A
    AB
    ABC
    ABCD
    ABCDE
    B
    BC
    BCD
    BCDE

    C
    CD
    CDE
    D
    DE
    E
     */
    @Test
    public void test(){

        String s = "ABCDEF";
        //从指定下标开始截取到最后
        String substring = s.substring(1);//BCDEF

        System.out.println("substring = " + substring);
        //按照范围截取 substring(开始下标, 结束下标)
        //[开始下标, 结束下标)
        String s1 = s.substring(1, 5);
        System.out.println("s1 = " + s1);
    }

    @Test
    public void test01(){

        String s = "ABC";
        //将 字符串 转为 字符数组
        char[] chars = s.toCharArray();
        //将 字符串 转为 字节数组
        byte[] bytes = s.getBytes();

        System.out.println("Arrays.toString(bytes) = " + Arrays.toString(bytes));

        byte[] b = {65,66,67};

        String s1 = new String(b);
        System.out.println("s1 = " + s1);


    }

    @Test
    public void test02() throws UnsupportedEncodingException {
        String s  ="中国";
        System.out.println("s.getBytes().length = " + s.getBytes().length);//6
        System.out.println("s.getBytes(\"GBK\").length = " + s.getBytes("GBK").length);//4


        String s1 = "A";
        System.out.println("---------");
        System.out.println("s.getBytes(\"UTF-8\").length = " + s1.getBytes("UTF-8").length);
        System.out.println("s1.getBytes().length = " + s1.getBytes().length);
        System.out.println("s1.getBytes(\"GBK\").length = " + s1.getBytes("GBK").length);
    }

    @Test
    public void test03() throws UnsupportedEncodingException {

        String s  ="你好";

        byte[] bytes = s.getBytes("UTF-8");

        String s1 = new String(bytes,"GBK");
        //编码不一致会导致乱码
        System.out.println("s1 = " + s1);


    }
}
