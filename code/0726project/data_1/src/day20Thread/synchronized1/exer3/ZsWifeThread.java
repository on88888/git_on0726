package day20Thread.synchronized1.exer3;

public class ZsWifeThread extends Thread {



    @Override
    public void run() {
        Account.take();
    }
}
