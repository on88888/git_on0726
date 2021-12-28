package day18IO.src.com.atguigu.reader;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/*
字符输入流完成数据读取
    一次读取一个字符 当没有数据时返回-1


 */
public class ReaderTest {

    @Test
    public void test01() throws IOException {
    //1.创建字符输入流对象
        Reader r = new FileReader("D:/w.txt");
        //2.读入数据
        //一次读取一个字符
        int read = r.read();
        System.out.println("read = " + read+" --> "+(char)read);

        read = r.read();
        System.out.println("read = " + read+" --> "+(char)read);


        read = r.read();
        System.out.println("read = " + read);
        //3.关流

        r.close();

    }
}
