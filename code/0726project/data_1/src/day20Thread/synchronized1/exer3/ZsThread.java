package day20Thread.synchronized1.exer3;

//张三线程
public class ZsThread extends Thread {


    @Override
    public void run() {

      Account.take();

    }
}
