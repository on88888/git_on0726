package day19IOAndThread.src.com.atguigu.io.print;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Test2 {
    @Test
    public void test01(){
        System.err.println("你好世界");
    }

    @Test
    public void test02() throws FileNotFoundException {

        System.out.println("你好世界");

        PrintStream ps = new PrintStream("D:/p3.txt");

        System.setOut(ps);



        System.out.println("我不困 我很饿");

    }
}
