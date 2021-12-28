package day20Thread.deadlock;
/*

死锁：互相持有对方的锁资源不放弃
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

            Object goods = new Object();
            Object money = new Object();

            SuperMarket s1 = new SuperMarket(goods, money);


            GoodsThread g1 = new GoodsThread(goods, money);


            s1.start();
            Thread.sleep(5000);
            g1.start();






    }


}
