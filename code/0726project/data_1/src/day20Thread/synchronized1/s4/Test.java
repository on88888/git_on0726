package day20Thread.synchronized1.s4;

/*
三个窗口卖一百张票
    锁的范围太大： 导致一个线程 一直做功 其他线程 不能参与 单线程程序
    锁的范围太小：没有同步效果
       将操作共享数据的内容放到同步代码块中
 */
public class Test {

    public static void main(String[] args) {

        //接口对象的创建
        TicketRunnable runnable = new TicketRunnable();

        Thread t1 = new Thread(runnable, "窗口一");
        Thread t2 = new Thread(runnable, "窗口二");
        Thread t3 = new Thread(runnable, "窗口三");


        t1.start();
        t2.start();
        t3.start();

    }
}
