package day19IOAndThread.src.com.atguigu.io.print;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Test {

    @org.junit.Test
    public void test01() throws FileNotFoundException {

        PrintStream ps = new PrintStream("D:/p.txt");


        ps.println("你好");
        ps.println("世界");

        ps.close();

    }

    @org.junit.Test
    public void test02() throws FileNotFoundException {

        PrintWriter p2 = new PrintWriter("D:/p1.txt");

        p2.println("你好");

        p2.close();


    }
}
