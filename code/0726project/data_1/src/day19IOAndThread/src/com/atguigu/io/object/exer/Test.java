package day19IOAndThread.src.com.atguigu.io.object.exer;

import java.io.*;
import java.util.ArrayList;

public class Test {

    @org.junit.Test
    public void test02() throws IOException, ClassNotFoundException {

        //对象输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("list.txt"));
        //读取数据

        Object o = ois.readObject();

        //将Object --> ArrayList
        ArrayList<Student> l = (ArrayList)o;

        for (Student o1 : l) {

            System.out.println("o1 = " + o1);
        }



        //展示数据
        System.out.println(o);

        //关闭资源

        ois.close();



    }

    @org.junit.Test
    public void test01() throws IOException {
        Student s1 = new Student("张三", 10);
        Student s2 = new Student("李四", 10);
        Student s3 = new Student("王五", 10);

        ArrayList<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);


        //创建对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("list.txt"));
        //写出数据

        oos.writeObject(list);


        //关闭资源
        oos.close();


    }
}
