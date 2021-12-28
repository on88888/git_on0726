package day18IO.src.com.atguigu.writer;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/*
字符输出流
    可以写出 数字  ---> 字符
    字符串
    字节数组


 */
public class WriterTest1 {

    @Test
    public void test01() throws IOException {
        //1.创建字符输出流对象
        Writer w = new FileWriter("D:/w.txt",true);
        //2.写出数据
        w.write("666");
        w.write("你好");
        w.write(67);
        w.write("世界");
        //3.关流
        w.close();

    }
    @Test
    public void test02() throws IOException {
        //1.创建字符输出流对象
        Writer w = new FileWriter("D:/w1.txt");
        //2.写出数据

        char[] cs = {'A','B','C'};
        w.write(cs);
        //3.关流

        w.close();



    }
}
