package day20Thread.synchronized1.exer2;


public class ZsWifeThread extends Thread {

    Account account ;

    public ZsWifeThread(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.take();
    }

}
