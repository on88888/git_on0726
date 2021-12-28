package day18IO.src.com.atguigu.inputstream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

public class Test2 {
/*

int len: 读取的有价值信息的数量

 */
@Test
public void test02() throws Exception {

    //1.创建字节输入流对象
    InputStream is = new FileInputStream("D:/d1.txt");
    //2.数据读取
    //创建字节数组
    byte[] bs = new byte[6];
    int len = is.read(bs);
    while (len!=-1){
      //  String s = new String(bs);
        String s = new String(bs, 0, len);
        System.out.println(s);
        //继续读
        len = is.read(bs);
    }

    //3.关流
    is.close();
}


    @Test
    public void test01() throws Exception {
        //1.创建输入流对象
        InputStream is = new FileInputStream("D:/d1.txt");
        //2.读取数据
        byte[] bs = new byte[2];
        System.out.println("Arrays.toString(bs) = " + Arrays.toString(bs));
        //第一次读取
        int len = is.read(bs);
        System.out.println("第一次读取 = " + Arrays.toString(bs)+" ,len = "+len+", "+new String(bs));
        //第二次读取
        len = is.read(bs);
        System.out.println("第二次读取 = " + Arrays.toString(bs)+" ,len = "+len+", "+new String(bs));
        //第三次读取
        len = is.read(bs);
        System.out.println("第三次读取 = " + Arrays.toString(bs)+" ,len = "+len+", "+new String(bs));
        //第四次读取
        len = is.read(bs);
        System.out.println("第三次读取 = " + Arrays.toString(bs)+" ,len = "+len+", "+new String(bs));
        //3.关流
        is.close();
    }
}
