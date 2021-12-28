package day13ExceptionAndAPI.src.com.atguigu.exception.e1;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*
编译时异常： 写完代码就报错  必须处理
FileNotFoundException

InterruptedException
 */
public class CheckedException {

    @Test
    public void test02() throws InterruptedException {

        Thread.sleep(1000);

    }




    @Test
    public void test01() throws FileNotFoundException {

        new FileInputStream("d:/a.txt");


    }
}
