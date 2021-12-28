package day19IOAndThread.src.com.atguigu.io.buffer.convert;

import org.junit.Test;

import java.io.*;
/*

    转换流：
        1. 将字节输入流 转为 字符输入流
                InputStreamReader

        2.将字节输出流转为字符输出流




 */
public class Test1 {

    @Test
    public void test04() throws IOException {
        //将字节输出流转为字符输出流

        System.out.println(666);
        /*
         System.out 是字节流
         OutputStreamWriter： 字符流
         */

        OutputStreamWriter osw = new OutputStreamWriter(System.out);

        osw.write("我不困");



        osw.close();


    }



    @Test
    public void test01() throws IOException {
        //1.创建字符流对象
        Reader r = new FileReader("D:/a.txt");
        //2.读取数据

        int read = r.read();

        System.out.println(read+" ---> "+(char)read);//20013 ---> 中

        //3.关闭资源

        r.close();

    }

    @Test
    public void test02() throws IOException {
        //1.先使用字节流读取
        //2.再将字节流转为指定编码的字符流
        InputStream is = new FileInputStream("D:/a.txt");
        InputStreamReader isr = new InputStreamReader(is, "gbk");

        int read = isr.read();
        System.out.println(read+" ---> "+(char)read);//20013 ---> 中
        //3.关闭资源
        isr.close();
    }

    @Test
    public void test03() throws IOException {

        //System.in; 字节输入流
        //字符输入流
        InputStreamReader isr = new InputStreamReader(System.in);

        BufferedReader br = new BufferedReader(isr);

        System.out.println("请您输入一句话");

        String line = br.readLine();

        System.out.println("line = " + line);


    }
}
