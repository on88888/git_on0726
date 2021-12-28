package day20Thread.notify;
/*
线程通信：
    wait(): 线程会进入到 阻塞状态

    notify()： 随机唤醒一条等待的线程

    notifyAll(): 唤醒所有等待的线程

  这三个方法 都来源于 Object

 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        new Test().wait();

    }
}
