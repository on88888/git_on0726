package day18IO.src.com.atguigu.inputstream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/*
字节输入流读取数据
        一次读取一个字节的数据 如果采用此方式读取汉字 会乱码 无法解析
        如果读到文件末尾 会返回-1

 */
public class Test1 {

    @Test
    public void test03() throws IOException {

        //1.创建字节输入流对象
        InputStream is = new FileInputStream("D:/d1.txt");

        //2.读取数据
        //记录读取的结果
        int i = 0;
        while ((i=is.read())!=-1){
            //展示数据
            System.out.println((char)i);//A
        }
        //3.关闭流
        is.close();
    }



    @Test
    public void test02() throws IOException {

        //1.创建字节输入流对象
        InputStream is = new FileInputStream("D:/d1.txt");

        //2.读取数据
        int i = is.read();
        while (i!=-1){
            //展示数据
            System.out.println((char)i);
            //再读一次
            i = is.read();

        }
        //3.关闭流
        is.close();
    }

    @Test
    public void test01() throws IOException {
        //1.创建字节输入流
        InputStream is = new FileInputStream("D:/d1.txt");
        //2.数据读取
        int i = is.read();
        System.out.println("i = " + i +" -- >"+(char)i);

        i = is.read();
        System.out.println("i = " + i +" -- >"+(char)i);

        i = is.read();

        System.out.println("i = " + i );

        //3.关流
        is.close();
    }
}
