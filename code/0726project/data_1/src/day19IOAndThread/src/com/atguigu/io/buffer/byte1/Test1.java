package day19IOAndThread.src.com.atguigu.io.buffer.byte1;

import org.junit.Test;

import java.io.*;

/*
字节缓冲流
    字节缓冲输入流
            BufferedInputStream  底层有一个缓冲数组  byte[] 8192
    字节缓冲输出流
            BufferedOutputStream   底层有一个缓冲数组  byte[] 8192



 */
public class Test1 {
    @Test
    public void test02() throws IOException {
        BufferedOutputStream  bos = new BufferedOutputStream(new FileOutputStream("D:/bos.txt")) ;

        bos.write(65);

        //刷出缓冲区的数据
         bos.flush();
         bos.close();
    }

    @Test
    public void test01() throws IOException {

        FileInputStream fis = new FileInputStream("D:/a.txt");
       // BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedInputStream bis = new BufferedInputStream(fis, 8192*10);



        bis.close();


    }

}
