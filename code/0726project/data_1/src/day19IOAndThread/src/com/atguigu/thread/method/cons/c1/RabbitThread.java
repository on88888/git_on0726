package day19IOAndThread.src.com.atguigu.thread.method.cons.c1;

public class RabbitThread extends Thread {

    RabbitThread(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println(getName()+" ---> 跑");
    }
}
