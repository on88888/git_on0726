package day19IOAndThread.src.com.atguigu.io.review;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
字符流完成文件复制


使用 字符输入流 读入数据


使用字符输出流写出数据
 */
public class Copy {


    public static void main(String[] args) {
        //1.创建流对象
        FileReader reader =null;
        FileWriter fw =null;
        try {
            reader = new FileReader("D:/a.txt");

            fw = new FileWriter("D:/b.txt");
            //2.读取数据
            char[] cs = new char[2];

            int len = reader.read(cs);

            while (len!=-1){

                fw.write(cs, 0, len);
                //继续读
                len = reader.read(cs);

            }

            System.out.println("复制完毕");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        //3.关流



    }
}
