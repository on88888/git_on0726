package day20Thread.synchronized1.exer2;

public class Account {

    private   int balance = 500;
    //取钱
    public void withDraw(int money){

        balance-=money;
    }

    //获取余额
    public int getBalance() {
        return balance;
    }


    //取钱操作
    public synchronized void take() {
        //取钱校验
        if (balance >= 400) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            withDraw(400);

            System.out.println(Thread.currentThread().getName() + "取钱成功 400元 ，余额是：" + this.getBalance() + " 元");

        } else {

            System.out.println(Thread.currentThread().getName() + "取钱失败 ，余额是：" + this.getBalance() + " 元");

        }


    }
}
