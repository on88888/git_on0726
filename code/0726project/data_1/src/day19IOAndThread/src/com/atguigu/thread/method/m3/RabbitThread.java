package day19IOAndThread.src.com.atguigu.thread.method.m3;

public class RabbitThread  extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            System.out.println(getName()+" 跑");
        }
    }
}

class DogThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            //线程的礼让
            Thread.yield();
            System.out.println(getName()+" 跑");
        }
    }
}
