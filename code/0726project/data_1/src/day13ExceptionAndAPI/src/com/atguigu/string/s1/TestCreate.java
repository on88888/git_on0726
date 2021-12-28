package day13ExceptionAndAPI.src.com.atguigu.string.s1;

import org.junit.Test;

/*
如何构建一个字符串



 */
public class TestCreate {

    @Test
    public void test01(){
        int m = 10;
        String s = ""+m;
    }
    @Test
    public void test02(){
        //构造器的方式

        String s1 = new String("你好");

        byte[] bs = {65,66,67,68};
        String s = new String(bs);
        System.out.println("s = " + s);
            //将 字节数组内指定位置的数据变为 字符串
        String s2 = new String(bs,  1, 2);
        System.out.println("s2 = " + s2);


        char [] cs = {'A','B','C','D'};
        //char[] -->字符串
        String s3 = new String(cs);

        System.out.println("s3 = " + s3);
        //将 字符数组内指定位置的数据变为 字符串
        String s4 = new String(cs, 1, 2);
        System.out.println("s4 = " + s4);




    }
    @Test
    public void test03(){
        //使用字符串内的方法
        int m = 10;
        String s = String.valueOf(m);
        System.out.println("s = " + s);


        String s1 = String.valueOf(3.14);
        System.out.println("s1 = " + s1);


        char [] cs = {'中','国','你','好'};

        String s2 = String.copyValueOf(cs);

        System.out.println("s2 = " + s2);
    }
}
