package day19IOAndThread.src.com.atguigu.thread.create.c1;

//创建兔子线程
public class RabbitThread extends Thread {

    @Override
    public void run() {

        while (true){
            System.err.println("兔子跑  add Oil  addOil...........");
        }

    }

}
