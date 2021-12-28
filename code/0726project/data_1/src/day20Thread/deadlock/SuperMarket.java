package day20Thread.deadlock;

/**
 * 超市线程
 */
public class SuperMarket extends Thread {
    Object goods;
    Object money;

    public SuperMarket(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {
        synchronized (goods){
            System.out.println("SuperMarket 先给货");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (money){
                System.out.println("SuperMarket 再给钱");
            }
        }
    }
}

