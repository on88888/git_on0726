package day19IOAndThread.src.com.atguigu.thread.method.m1;

public class CatRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            System.out.println(Thread.currentThread().getName()+" ---> 跑");
        }
    }
}
