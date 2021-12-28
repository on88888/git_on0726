package day19IOAndThread.src.com.atguigu.io.buffer.char1;

import java.io.*;

/*
字符缓冲流  处理流
    字符缓冲输入流
           BufferedReader  缓冲数组  char[] 8192
            readLine() 当没有数据时 返回 null
    字符缓冲输出流
           BufferedWriter  缓冲数组  char[] 8192

           newLine();换行

 */
public class Test {

    @org.junit.Test
    public void test02() throws IOException {
        //1.创建字符缓冲输出流对象

        Writer writer = new FileWriter("D:/bw.txt");
        BufferedWriter bw = new BufferedWriter(writer);
        //2.写出数据
        bw.write("你好");
       // bw.write("\n");
        bw.newLine();
        bw.write("世界");
        //写出换行
        bw.newLine();
        bw.write("我不困");



        //3.关流
        bw.close();


    }


    @org.junit.Test
    public void test01() throws IOException {

        Reader r = new FileReader("D:/a.txt");
        BufferedReader br = new BufferedReader(r);

        String s = br.readLine();
        System.out.println("s = " + s);

        s = br.readLine();
        System.out.println("s = " + s);
        //第三次读
        s = br.readLine();
        System.out.println("s = " + s);

        s = br.readLine();
        System.out.println("s = " + s);


        br.close();
    }

}
