package day19IOAndThread.src.com.atguigu.io.datastream;

import java.io.*;

/*
数据流：
    可以写出包含数据特性的数据
        int
        double
        boolean

    //1.数据输出流
        DataOutputStream

    //2.数据输入流
        DataInputStream

   注意：
        1.读写顺序一致
        2.数据流只有字节流 没有字符流
 */
public class Test {
    @org.junit.Test
    public void test02() throws IOException {
        //1.创建输入流对象
        InputStream is = new FileInputStream("D:/dos.txt");
        DataInputStream dis = new DataInputStream(is);

        //2.读取数据

        double v = dis.readDouble();
        System.out.println("double = " + v);

        boolean b = dis.readBoolean();
        System.out.println("boolean = " + b);

        int i = dis.readInt();
        System.out.println("int = " + i);

        String s = dis.readUTF();

        System.out.println("String = " + s);


        //3.关闭资源
        dis.close();

    }



    @org.junit.Test
    public void test01() throws IOException {
        //1.创建流对象
        OutputStream os = new FileOutputStream("D:/dos.txt");
        DataOutputStream dos = new DataOutputStream(os);

        //2.写出数据
        dos.writeDouble(3.14);
        dos.writeBoolean(false);
        dos.writeInt(65);
        dos.writeUTF("我不困");
        //3.关闭资源
        dos.close();
    }
}
