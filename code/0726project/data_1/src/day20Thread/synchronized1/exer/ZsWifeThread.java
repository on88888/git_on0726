package day20Thread.synchronized1.exer;

public class ZsWifeThread extends Thread {

    Account account ;

    public ZsWifeThread(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        synchronized (account){
            //取钱校验
            if(account.getBalance()>=400){

                account.withDraw(400);

                System.out.println(this.getName()+"取钱成功 400元 ，余额是："+account.getBalance()+" 元");

            }else{

                System.out.println(this.getName()+"取钱失败 ，余额是："+account.getBalance()+" 元");
            }
        }
    }
}
