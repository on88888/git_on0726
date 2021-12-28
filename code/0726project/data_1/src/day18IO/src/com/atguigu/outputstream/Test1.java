package day18IO.src.com.atguigu.outputstream;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
字节输出流：
      1. 写出数据时 如果没有此文件 会自动创建
      2.写出的数字会转为字符
      3.new FileOutputStream(路径, true); 在原有数据后追加

 */
public class Test1 {

    @Test
    public void test03() throws IOException {
        //1.创建输出流对象
            OutputStream os = new FileOutputStream("D://d1.txt");

        //2.写出数据
        byte[] bs = {65,66,67,68,69};
        //写出指定数据  写出 bs数组内 下标从1开始 连续两个数据
        os.write(bs, 1, 2);
        //3.关流

        os.close();


    }


    @Test
    public void test02() throws IOException {

        //1.创建输出流对象
        FileOutputStream f = new FileOutputStream("D:/dd.txt");
        //2.写出数据
        String s = "我只中意你";

        byte[] bytes = s.getBytes();
        f.write(bytes);

        String s1="\n是的";

        f.write(s1.getBytes());


        //3.关闭资源

        f.close();

        System.out.println("写出完毕");



    }



    @Test
    public void test01() throws IOException {
        //1.创建字节输出流对象
       // OutputStream os =  new FileOutputStream("D:/d.txt");
        OutputStream os =  new FileOutputStream("D:/d.txt", true);
        //2.写出数据

        os.write(97);
        os.write(98);

        //3.关流

        os.close();
        System.out.println("写出完毕");

    }
}
