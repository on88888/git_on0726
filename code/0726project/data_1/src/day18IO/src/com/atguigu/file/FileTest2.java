package day18IO.src.com.atguigu.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest2 {
    /*
    相对路径：相对当前模块而言的路径
    绝对路径： 磁盘的物理地址
    规范路径：磁盘的物理地址 可以识别../../
     */

    @Test
    public void test01() throws IOException {

        File f = new File("D:\\software\\神器.zip");

        System.out.println("f.getPath() = " + f.getPath());
        System.out.println("f.getAbsolutePath() = " + f.getAbsolutePath());
        System.out.println("f.getCanonicalPath() = " + f.getCanonicalPath());


    }
    @Test
    public void test02() throws IOException {
        File f = new File("a.txt");

        System.out.println("f.getPath() = " + f.getPath());
        System.out.println("f.getAbsolutePath() = " + f.getAbsolutePath());
        System.out.println("f.getCanonicalPath() = " + f.getCanonicalPath());

    }


    @Test
    public void test03() throws IOException {
        File f = new File("../../a.txt");

        System.out.println("f.getPath() = " + f.getPath());
        System.out.println("f.getAbsolutePath() = " + f.getAbsolutePath());
        System.out.println("f.getCanonicalPath() = " + f.getCanonicalPath());

    }
}
