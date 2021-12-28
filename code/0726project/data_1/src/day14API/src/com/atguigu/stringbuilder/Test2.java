package day14API.src.com.atguigu.stringbuilder;

import org.junit.Test;

public class Test2 {

    @Test
    public void test00(){
        //获取当前时间的毫秒数
        long l = System.currentTimeMillis();

    }

    @Test
    public void test02(){

        testBuffer();

    }

    @Test
    public void test03(){
        testBuilder();
    }
    private void testBuilder() {
        long start = System.currentTimeMillis();
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < 10_000000; i++) {
            s.append(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuilder 用时："+(end-start)+" ms");//22
    }

    private void testBuffer() {
        long start = System.currentTimeMillis();
        StringBuffer s = new StringBuffer();

        for (int i = 0; i < 10_000000; i++) {
            s.append(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuffer 用时："+(end-start)+" ms");//61

    }

    @Test
    public void test01(){
        testString();

    }

    public void testString() {
        long start = System.currentTimeMillis();
        String s = "";

        for (int i = 0; i < 10_0000; i++) {
            s+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("String 用时："+(end-start)+" ms");//32184
    }
}
