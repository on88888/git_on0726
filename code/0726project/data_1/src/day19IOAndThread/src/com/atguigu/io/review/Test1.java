package day19IOAndThread.src.com.atguigu.io.review;

import org.junit.Test;

import java.io.File;
import java.util.Date;

/*
File:
    获取名字
    获取最后修改时间
    获取【文件】的大小

    获取路径

    判断

    创建
      mkdir();
      mkdirs();

   展示 DIR


IO流：

    四大基流
        InputStream
        OutputStream

        Reader
        Writer

    字节流
        FileInputStream
        FileOutputStream
    字符流：
        FileReader
        FileWriter
 */
public class Test1 {

    @Test
    public void test01(){

        File file = new File("D:/a.txt");//2021-8-20 9:15:10

        System.out.println("new Date(file.lastModified()).toLocaleString() = " + new Date(file.lastModified()).toLocaleString());
        Date date = new Date(120, 8, 13);
        long time = date.getTime();
        //修改最后修改时间
        file.setLastModified(time);
        System.out.println("new Date(file.lastModified()).toLocaleString() = " + new Date(file.lastModified()).toLocaleString());


    }
}
