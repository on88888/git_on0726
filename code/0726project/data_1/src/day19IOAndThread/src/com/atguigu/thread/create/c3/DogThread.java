package day19IOAndThread.src.com.atguigu.thread.create.c3;

public class DogThread extends Thread {

    int i = 10;

    @Override
    public void run() {
        System.out.println("小狗跑......");
    }
}
