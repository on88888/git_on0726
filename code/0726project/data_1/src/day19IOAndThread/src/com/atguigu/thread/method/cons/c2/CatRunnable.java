package day19IOAndThread.src.com.atguigu.thread.method.cons.c2;

public class CatRunnable implements Runnable {
    @Override
    public void run() {
        //Thread.currentThread().getName()获取当前线程的名字
        System.out.println( Thread.currentThread().getName()+ "  跑");
    }
}
