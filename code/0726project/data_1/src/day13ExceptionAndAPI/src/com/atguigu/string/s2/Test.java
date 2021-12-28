package day13ExceptionAndAPI.src.com.atguigu.string.s2;
/*
空字符串的介绍
    isEmpty()--> true
    length()---> 0


 */
public class Test {
    @org.junit.Test
    public void test03(){
        //null调用任何资源 都是  NullPointerException
        String s = null;
     //   NullPointerException
        System.out.println("s.length() = " + s.length());


    }



    @org.junit.Test
    public void test02(){

        String s = "1你2, .";
        //返回字符串内元素的数量
        int length = s.length();

        System.out.println("length = " + length);


    }

    @org.junit.Test
    public void test01(){
        String s = "";

        boolean empty = s.isEmpty();

        System.out.println("empty = " + empty);


    }
}
