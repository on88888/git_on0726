package day19IOAndThread.src.com.atguigu.thread.create.c1;
/*
龟兔赛跑

    乌龟线程
    兔子线程

创建多线程 方式一：
    1.新建一个类 继承 Thread

    2.重写run()  写多线程的目的

    3.启动线程


 */
public class Test {
    public static void main(String[] args) {

        //1.创建线程对象
        RabbitThread r = new RabbitThread();
        //2.启动线程
        r.start();
        //1.创建乌龟线程
        TortoiseThread t = new TortoiseThread();
        //2.启动乌龟线程
        t.start();
    }
}
