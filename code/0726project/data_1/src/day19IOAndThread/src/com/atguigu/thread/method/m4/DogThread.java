package day19IOAndThread.src.com.atguigu.thread.method.m4;

public class DogThread extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {

            System.out.println(getName()+" ----> 跑");

        }

        System.out.println("this.isAlive() = " + this.isAlive());
    }
}
