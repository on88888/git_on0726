package day19IOAndThread.src.com.atguigu.thread.create.c2;

public class RabbitRunnable implements Runnable {
    @Override
    public void run() {
        while (true){
            System.out.println("兔子跑 加油跑...........");
        }
    }
}

class TortoiseRunnable implements Runnable{


    @Override
    public void run() {
        while (true){
            System.out.println("乌龟跑 加油跑...........");
        }
    }
}
