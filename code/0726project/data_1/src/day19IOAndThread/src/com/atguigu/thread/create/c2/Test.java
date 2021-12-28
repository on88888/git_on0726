package day19IOAndThread.src.com.atguigu.thread.create.c2;
/*
创建多线程的方式二：
    1.创建一个类 实现 Runnable

    2.重写run

    3.启动线程

 */
public class Test {

    public static void main(String[] args) {
        //1.创建接口实现类对象
        RabbitRunnable r = new RabbitRunnable();
        //2.将此对象作为参数进行传递到 Thread类中
        Thread t1 = new Thread(r);
        //3.启动
        t1.start();
        //1.创建实现类对象
        TortoiseRunnable t = new TortoiseRunnable();
        //2.将此对象作为参数进行传递到 Thread类中
        Thread t2 = new Thread(t);

        //3.启动线程
        t2.start();
    }
}
