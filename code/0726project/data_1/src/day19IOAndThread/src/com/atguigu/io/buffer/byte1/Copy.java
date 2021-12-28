package day19IOAndThread.src.com.atguigu.io.buffer.byte1;

import java.io.*;

/*

使用字节缓冲流完成文件的复制
 */
public class Copy {

    public static void main(String[] args) {

        //1.创建流对象
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\software\\Ra2_Yuri(2in1).7z"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\c\\a.7z"));) {

            long start = System.currentTimeMillis();

            byte[] bs = new byte[1024 * 80];

            int len = 0;
            while ((len = bis.read(bs)) != -1) {

                bos.write(bs, 0, len);
            }
            long end = System.currentTimeMillis();
            System.out.println("复制完毕：" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
