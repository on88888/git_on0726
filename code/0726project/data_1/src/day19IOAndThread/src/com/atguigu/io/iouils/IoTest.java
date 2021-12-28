package day19IOAndThread.src.com.atguigu.io.iouils;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

public class IoTest {

    @Test
    public void test02() throws IOException {

        IOUtils.copy(new FileInputStream("D:/ff.txt"), new FileOutputStream("D:/f1.txt"));

    }



    @Test
    public void test01() throws IOException {
        byte[]b = {65,66};
        //写出字节数据
        IOUtils.write(b,new FileOutputStream("D:/ff.txt"));


        FileWriter fileWriter = new FileWriter("D://dd.txt");
        //写出字符需要关流
        IOUtils.write("ABCD", fileWriter);
        IOUtils.close(fileWriter);
    }
}
