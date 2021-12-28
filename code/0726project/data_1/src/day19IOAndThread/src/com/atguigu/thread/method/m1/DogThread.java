package day19IOAndThread.src.com.atguigu.thread.method.m1;

public class DogThread extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            System.out.println(getName()+" 跑  ----》");
        }
    }
}
