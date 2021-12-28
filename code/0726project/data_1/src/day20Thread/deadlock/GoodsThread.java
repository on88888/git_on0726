package day20Thread.deadlock;

//供货商
class GoodsThread extends Thread{
    Object goods;
    Object money;

    public GoodsThread(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }
    @Override
    public void run() {
        synchronized (money){
            System.out.println("GoodsThread 先给钱");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (goods){

                System.out.println("GoodsThread 再给货");
            }
        }
    }
}