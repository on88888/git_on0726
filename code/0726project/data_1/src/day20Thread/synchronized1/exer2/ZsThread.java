package day20Thread.synchronized1.exer2;


//张三线程
public class ZsThread extends Thread {

    Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.take();

    }


}
