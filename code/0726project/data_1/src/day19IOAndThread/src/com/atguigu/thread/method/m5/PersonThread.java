package day19IOAndThread.src.com.atguigu.thread.method.m5;

public class PersonThread extends Thread {


    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println(getName()+"跑 "+(i+1));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
