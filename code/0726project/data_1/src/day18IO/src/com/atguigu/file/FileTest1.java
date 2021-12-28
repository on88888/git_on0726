package day18IO.src.com.atguigu.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/*
File:代表文件或者文件夹
 */
public class FileTest1 {
    @Test
    public void test05() throws IOException {


        File f1 = new File("D:/a/b");
        /*
        mkdir():创建单级目录
        mkdirs():创建多级目录
         */
        f1.mkdirs();

        //删除目录只能删除空目录
        f1.delete();



    }



    @Test
    public void test04() throws IOException {
        //创建操作

        File f1 = new File("D://c.txt");

        //不存在创建
        if(!f1.exists()){
            //创建文件
            f1.createNewFile();
        }else{
            //存在删除
            f1.delete();
        }


    }




    @Test
    public void test03(){
         File f1 = new File("D:\\software", "神器1.zip");
       // File f2 = new File("D:\\software");
            //判断是否为文件夹
        boolean b = f1.isDirectory();

        System.out.println("b = " + b);
        //判断是否为文件
        boolean file = f1.isFile();
        System.out.println("file = " + file);

        //判断文件或者文件夹是否存在
        boolean exists = f1.exists();
        System.out.println("exists = " + exists);
    }



    @Test
    public void test02(){
        //创建File对象
       // File f2 = new File("D:\\software", "神器.zip");
       File f2 = new File("D:\\software");

        System.out.println("f2 = " + f2);//D:\software\神器.zip
        //获取名字
        String name = f2.getName();

        System.out.println("name = " + name);// 神器.zip
        //最后修改时间
     /*   long l = f2.lastModified();
        Date date = new Date(l);
        System.out.println("date = " + date);
        System.out.println("date.toLocaleString() = " + date.toLocaleString());*/

        System.out.println("new Date(f2.lastModified()).toLocaleString() = " + new Date(f2.lastModified()).toLocaleString());

        //获取文件的长度
        long length = f2.length();
        System.out.println("length = " + length);


    }



    @Test
    public void test01(){
        //创建File对象
        File f1 = new File("D:\\a.txt");
        File f4 = new File("D:/a.txt");
        File f5 = new File("D:"+File.separator+"a.txt");

        File f2 = new File("D:\\software", "神器.zip");


        File f3 = new File(new File("D:\\software"), "神器.zip");


    }
}
