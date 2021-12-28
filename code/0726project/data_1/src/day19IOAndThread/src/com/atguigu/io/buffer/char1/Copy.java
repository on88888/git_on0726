package day19IOAndThread.src.com.atguigu.io.buffer.char1;

import org.junit.Test;

import java.io.*;

/*
字符缓冲流 文件复制


 */
public class Copy {

    @Test
    public void test01(){

        //1.创建流对象


        try (
                BufferedReader br = new BufferedReader(new FileReader("D:/bw.txt"));

                BufferedWriter bw = new BufferedWriter(new FileWriter("D:/w.txt"))) {

            //2.完成读写

            String line = br.readLine();
            while (line!=null){

                System.out.println("line = " + line);
                //将读到一行数据写出

                bw.write(line);
                //写出一行后换行
                bw.newLine();

                //继续读
                line= br.readLine();

            }

            System.out.println("复制完毕");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //3.关流

    }
}
