package day18IO.src.com.atguigu.file;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

public class FileTest3 {

    @Test
    public void test01() {
        File f = new File("D:\\software");
//将名字存到数组内
        String[] list = f.list();

        for (String s : list) {
            System.out.println("s = " + s+"---> "+ s.toUpperCase());
        }

    }

    @Test
    public void test02(){

        File f = new File("D:\\software");
        //将file对象存储到数组内
        File[] fs = f.listFiles();

        for (File file : fs) {

            System.out.println(file+" --> "+ new Date(file.lastModified()).toLocaleString());
        }

    }

    @Test
    public void test03(){
        //对展示内容进行过滤

        File f = new File("D:\\software");

        File[] files = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
               /* System.out.println("dir = " + dir);
                System.out.println("name = " + name);*/
               //过滤条件
                File file = new File(dir, name);
                //true 要显示什么 就让判断结果为true
                return file.isFile();// 只显示文件
            }
        });


        for (File file : files) {
            System.out.println("file = " + file);
        }

    }
}
