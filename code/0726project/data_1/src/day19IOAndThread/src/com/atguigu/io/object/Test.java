package day19IOAndThread.src.com.atguigu.io.object;

import java.io.*;

/*
使用对象流 进行对象的写出和读入
    1.对象输出流  对象写到磁盘上  序列化
        ObjectOutputStream



    2.对象输入流  将磁盘中存储的对象读到程序内  反序列化
        ObjectInputStream

注意：

            1.  NotSerializableException
               在进行序列化时  必须让所在的对象的类 实现 Serializable
            2. 序列化与反序列化的版本要一致

            3.如果某些属性不想被序列化 可以使用
               transient 进行修饰
               static 进行修饰

 */
public class Test {

    @org.junit.Test
    public void test01() throws IOException {



        Person p1 = new Person("朱茵", 20, 9999.9, '男');

        System.out.println(p1);
        //1.创建对象输出流
        OutputStream os = new FileOutputStream("D:/object.txt");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        //2.写出数据
        //写出对象
        oos.writeObject(p1);

        //3.关闭资源
        oos.close();
        System.out.println("写出完毕");

    }

    @org.junit.Test
    public void test02() throws IOException, ClassNotFoundException {

        //1.创建对象输入流
        InputStream is  = new FileInputStream("D:/object.txt");
        ObjectInputStream ois = new ObjectInputStream(is);
        //2.读取数据
        //
        Object o = ois.readObject();

        //3.展示信息

        System.out.println("o = " + o);


        //4.关闭资源

        ois.close();

    }
}
