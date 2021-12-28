package day18IO.src.com.atguigu.copy;

import java.io.*;

public class CopyTest1 {

    public static void main(String[] args) {
        //1.创建字节输入流对象
        InputStream is=null;
        OutputStream os =null;
        try {

            is = new FileInputStream("C:\\图片\\小新.gif");
            //创建字节输出流对象
            os = new FileOutputStream("D:/a.gif");
            //2.创建字节数组
            byte[] bs = new byte[1024];

            //3.读入数据
            int len = is.read(bs);
            while (len != -1) {
                //4.写出数据
                os.write(bs, 0, len);
                //继续读
                len = is.read(bs);
            }
            System.out.println("复制成功");
            //5.关流
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
