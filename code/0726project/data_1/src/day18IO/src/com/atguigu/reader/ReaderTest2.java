package day18IO.src.com.atguigu.reader;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;

public class ReaderTest2 {

    @Test
    public void test01() throws Exception {
    //1.创建字符输入流对象
        Reader r = new FileReader("D:/作文.txt");
        //2.读取数据

        int read = r.read();

        while (read!=-1){

            System.out.println((char)read);

            read = r.read();
        }

        //3.关流
        r.close();


    }

    @Test
    public void test03() throws Exception {

        //1.创建字符输入流对象
        Reader r = new FileReader("D:/w.txt");

        //2.读取数据
        char[] cs = new char[2];
        System.out.println("Arrays.toString(cs) = " + Arrays.toString(cs));
        //第一次读取
        int length = r.read(cs);

        System.out.println("length = " + length+","+ Arrays.toString(cs));
        //第二次读取
        length = r.read(cs);
        System.out.println("length = " + length+","+ Arrays.toString(cs));

        //第三次读取
        length = r.read(cs);
        System.out.println("length = " + length+","+ Arrays.toString(cs));
        //当没有数据时返回-1
        length = r.read(cs);
        System.out.println("length = " + length);

        //3.关流

        r.close();





    }
    @Test
    public void test04() throws Exception {
    //1.创建字符输入流

        Reader r  = new FileReader("D:/作文.txt");

        //2.读取数据

        char cs[] =new char[100];

        int length = r.read(cs);

        while (length!=-1){

            String s = new String(cs,0,length);

            System.out.println("s = " + s);

            //继续读
            length = r.read(cs);
        }


        //3.关流


    }
}
