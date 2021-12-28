package day18IO.src.com.atguigu.file;

import java.io.File;

public class Exer {

    public static void main(String[] args) {

        File file = new File("D:\\software");

        show(file);

    }

    public static void show(File f){

        File[] files = f.listFiles();

        for (File file : files) {

            if(file.isDirectory()){
                System.out.println("文件夹的名字是： "+ file.getName());
            show(file);
            }else{
                //如果不是文件夹 直接展示
                System.out.println(file.getName());
            }
        }




    }
}
