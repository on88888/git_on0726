package day20Thread.synchronized1.exer3;

public class Account {
    private  static int balance = 500;
    //取钱
    public static void withDraw(int money){

        balance-=money;
    }

    //获取余额
    public int getBalance() {
        return balance;
    }

    public static synchronized void take() {
        //取钱校验
        if (balance >= 400) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            withDraw(400);

            System.out.println(Thread.currentThread().getName() + "取钱成功 400元 ，余额是：" + balance + " 元");
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败 ，余额是：" + balance + " 元");
        }
    }
}
